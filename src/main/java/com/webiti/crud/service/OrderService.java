package com.webiti.crud.service;

import com.webiti.crud.dto.request.OrderRequest;
import com.webiti.crud.dto.response.OrderResponse;

import java.util.List;

public interface OrderService {
    List<OrderResponse> allOrders();
    List<OrderResponse> allOrderByUserId(Integer userId);
    OrderResponse createOrder(OrderRequest input);
    OrderResponse updateOrderById(Long id, OrderRequest input);
    OrderResponse getOrderById(Long id);
    void deleteOrderById(Long id);
}
