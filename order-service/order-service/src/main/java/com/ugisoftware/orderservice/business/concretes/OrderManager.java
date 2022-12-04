package com.ugisoftware.orderservice.business.concretes;

import com.ugisoftware.orderservice.business.abstracts.OrderService;
import com.ugisoftware.orderservice.dataAccess.OrderRepository;
import com.ugisoftware.orderservice.entities.concretes.Order;
import com.ugisoftware.orderservice.entities.concretes.OrderLinesItem;
import com.ugisoftware.orderservice.entities.dto.request.OrderLineItemsDto;
import com.ugisoftware.orderservice.entities.dto.request.OrderRequest;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class OrderManager implements OrderService{

    private final OrderRepository orderRepository;
    public OrderManager(OrderRepository orderRepository)
    {
        this.orderRepository=orderRepository;
    }
    public void placeOrder(OrderRequest orderRequest) {
        Order order=new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLinesItem> orderLineItems = orderRequest.getOrderLineItemsDtoList()
                .stream()
                .map(this::mapToDto)
                .toList();

        order.setOrderLineItemsList(orderLineItems);

        orderRepository.save(order);
    }

    private OrderLinesItem mapToDto(OrderLineItemsDto orderLineItemsDto) {
        OrderLinesItem orderLineItems = new OrderLinesItem();
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
        return orderLineItems;
    }



}