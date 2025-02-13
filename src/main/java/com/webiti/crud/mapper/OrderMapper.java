package com.webiti.crud.mapper;

import com.webiti.crud.dto.request.OrderRequest;
import com.webiti.crud.dto.response.OrderResponse;
import com.webiti.crud.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface OrderMapper {
    Order mapToOrder(OrderRequest orderRequest);
    OrderResponse mapToOrderResponse(Order order);
    void mapUpdateOrder(OrderRequest orderRequest, @MappingTarget Order order);
}
