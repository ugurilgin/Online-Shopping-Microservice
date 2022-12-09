package com.ugisoftware.orderservice.webapi.controllers.abstracts;

import java.util.concurrent.CompletableFuture;

import com.ugisoftware.orderservice.entities.dto.request.OrderRequest;

public interface IOrderController {
	 public CompletableFuture<String> placeOrder(OrderRequest orderRequest);

}
