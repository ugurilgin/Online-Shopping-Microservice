package com.ugisoftware.orderservice.business.abstracts;

import com.ugisoftware.orderservice.entities.dto.request.OrderRequest;

public interface OrderService {
    public void placeOrder(OrderRequest orderRequest);
}
