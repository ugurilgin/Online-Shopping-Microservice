package com.ugisoftware.orderservice.webapi.controllers;

import com.ugisoftware.orderservice.business.abstracts.OrderService;
import com.ugisoftware.orderservice.dataAccess.OrderRepository;
import com.ugisoftware.orderservice.entities.dto.request.OrderRequest;
import com.ugisoftware.orderservice.webapi.controllers.abstracts.IOrderController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
public class OrderController implements IOrderController {
    private OrderService orderService;
    public OrderController( OrderService orderService)
    {
        this.orderService=orderService;
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String placeOrder(@RequestBody OrderRequest orderRequest) {
        orderService.placeOrder(orderRequest);
        return "";
    }
}
