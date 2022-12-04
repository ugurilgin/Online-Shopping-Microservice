package com.ugisoftware.orderservice.webapi.controllers.abstracts;

import com.ugisoftware.orderservice.entities.dto.request.OrderRequest;

public interface IOrderController {
    public String placeOrder(OrderRequest orderRequest);

}
