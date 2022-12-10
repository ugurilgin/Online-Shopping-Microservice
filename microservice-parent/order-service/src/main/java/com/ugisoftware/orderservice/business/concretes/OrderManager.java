package com.ugisoftware.orderservice.business.concretes;

import com.ugisoftware.orderservice.business.abstracts.OrderService;
import com.ugisoftware.orderservice.dataAccess.OrderRepository;
import com.ugisoftware.orderservice.entities.concretes.Order;
import com.ugisoftware.orderservice.entities.concretes.OrderLinesItem;
import com.ugisoftware.orderservice.entities.dto.request.OrderLineItemsDto;
import com.ugisoftware.orderservice.entities.dto.request.OrderRequest;

import com.ugisoftware.orderservice.entities.dto.response.InventoryResponse;
import lombok.RequiredArgsConstructor;

import brave.Span;
import brave.Tracer;
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
    public OrderManager(OrderRepository orderRepository,WebClient.Builder webClient,Tracer tracer)
    {

        this.orderRepository=orderRepository;
        this.webClient=webClient;
        this.tracer=tracer;
    }
    public String placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLinesItem> orderLineItems = orderRequest.getOrderLineItemsDtoList()
                .stream()
                .map(this::mapToDto)
                .toList();

        order.setOrderLineItemsList(orderLineItems);

        List<String> skuCodes = order.getOrderLineItemsList().stream()
                .map(OrderLinesItem::getSkuCode)
                .toList();
        Span inventoryServiceLookup = tracer.nextSpan().name("InventoryServiceLookup");
        try (Tracer.SpanInScope isLookup = tracer.withSpanInScope(inventoryServiceLookup.start())) {
        // Call Inventory Service, and place order if product is in
        // stock
        inventoryServiceLookup.tag("call", "inventory-service");
        InventoryResponse[] inventoryResponsArray = webClient.build().get()
        		 .uri("http://inventory-service/api/inventory",
                        uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();

        boolean allProductsInStock = Arrays.stream(inventoryResponsArray)
                .allMatch(InventoryResponse::isInStock);

        if(allProductsInStock){
            orderRepository.save(order);
            return "Order Placed Successfully";
        } else {
            throw new IllegalArgumentException("Product is not in stock, please try again later");
        }
        }
        finally {
        	 inventoryServiceLookup.flush();
		}
    }

    private OrderLinesItem mapToDto(OrderLineItemsDto orderLineItemsDto) {
        OrderLinesItem orderLineItems = new OrderLinesItem();
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
        return orderLineItems;
    }



}