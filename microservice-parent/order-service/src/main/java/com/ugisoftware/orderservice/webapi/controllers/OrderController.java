package com.ugisoftware.orderservice.webapi.controllers;

import com.ugisoftware.orderservice.business.concretes.OrderManager;
import com.ugisoftware.orderservice.dataAccess.OrderRepository;
import com.ugisoftware.orderservice.entities.dto.request.OrderRequest;
import com.ugisoftware.orderservice.webapi.controllers.abstracts.IOrderController;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.CompletableFuture;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
public class OrderController implements IOrderController {
    private OrderManager orderService;
    public OrderController( OrderManager orderService)
    {
        this.orderService=orderService;
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @CircuitBreaker(name = "inventory", fallbackMethod = "fallbackMethod")
    @TimeLimiter(name = "inventory")
    @Retry(name = "inventory")
    public CompletableFuture<String> placeOrder(@RequestBody OrderRequest orderRequest) {
        
    	return CompletableFuture.supplyAsync(() -> orderService.placeOrder(orderRequest));
    }

    public CompletableFuture<String> fallbackMethod(OrderRequest orderRequest, RuntimeException runtimeException) {
        return CompletableFuture.supplyAsync(() -> "Oops! Something went wrong, please order after some time!");
    }
}
