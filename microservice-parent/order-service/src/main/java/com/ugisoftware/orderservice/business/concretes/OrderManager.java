package com.ugisoftware.orderservice.business.concretes;

import com.ugisoftware.orderservice.business.abstracts.OrderService;
import com.ugisoftware.orderservice.dataAccess.OrderRepository;
import com.ugisoftware.orderservice.entities.concretes.Order;
import com.ugisoftware.orderservice.entities.concretes.OrderLineItems;
import com.ugisoftware.orderservice.entities.dto.request.OrderLineItemsDto;
import com.ugisoftware.orderservice.entities.dto.request.OrderRequest;

import com.ugisoftware.orderservice.entities.dto.response.InventoryResponse;
import com.ugisoftware.orderservice.event.OrderPlacedEvent;

import lombok.RequiredArgsConstructor;

import brave.Span;
import brave.Tracer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class OrderManager implements OrderService{

    private final OrderRepository orderRepository;
    private final WebClient.Builder webClient;
    private final Tracer tracer;
    private final KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate;
    public OrderManager(OrderRepository orderRepository,WebClient.Builder webClient,Tracer tracer,KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate)
    {

        this.orderRepository=orderRepository;
        this.webClient=webClient;
        this.tracer=tracer;
        this.kafkaTemplate=kafkaTemplate;
    }
    public String placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDtoList()
                .stream()
                .map(this::mapToDto)
                .toList();

        order.setOrderLineItemsList(orderLineItems);

        List<String> skuCodes = order.getOrderLineItemsList().stream()
                .map(OrderLineItems::getSkuCode)
                .toList();

        Span inventoryServiceLookup = tracer.nextSpan().name("InventoryServiceLookup");

        try (Tracer.SpanInScope isLookup = tracer.withSpanInScope(inventoryServiceLookup.start())) {

            inventoryServiceLookup.tag("call", "inventory-service");
            // Call Inventory Service, and place order if product is in
            // stock
            InventoryResponse[] inventoryResponsArray = webClient.build().get()
                    .uri("http://inventory-service/api/inventory",
                            uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
                    .retrieve()
                    .bodyToMono(InventoryResponse[].class)
                    .block();

            boolean allProductsInStock = Arrays.stream(inventoryResponsArray)
                    .allMatch(InventoryResponse::isInStock);

            if (allProductsInStock) {
                orderRepository.save(order);
                kafkaTemplate.send("notificationTopic", new OrderPlacedEvent(order.getOrderNumber()));
                return "Order Placed Successfully";
            } else {
                throw new IllegalArgumentException("Product is not in stock, please try again later");
            }
        } finally {
            inventoryServiceLookup.flush();
        }
    }

    private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
        return orderLineItems;
    }
}



