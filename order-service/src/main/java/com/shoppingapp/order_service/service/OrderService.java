package com.shoppingapp.order_service.service;

import com.shoppingapp.order_service.dto.OrderRequest;

public interface OrderService {
    public void placeOrder(OrderRequest orderRequest);
}
