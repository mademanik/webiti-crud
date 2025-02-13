package com.webiti.crud.dto.request;

import com.webiti.crud.model.OrderStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderRequest {
    private Integer user_id;
    private Integer product_id;
    private int quantity;
    private double totalPrice;
    private OrderStatus status;
}
