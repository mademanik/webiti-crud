package com.webiti.crud.dto.response;

import com.webiti.crud.model.OrderStatus;
import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class OrderStatResponse {
    private Integer totalOrders;
    private Map<OrderStatus, Long> statuses;
}
