package com.webiti.crud.dto.response;

import com.webiti.crud.model.OrderStatus;
import com.webiti.crud.model.Product;
import com.webiti.crud.model.User;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class OrderResponse {
    private Long id;
    private UserResponse user;
    private ProductResponse product;
    private int quantity;
    private double totalPrice;
    private OrderStatus status;
    private Date createdAt;
    private Date updatedAt;
}
