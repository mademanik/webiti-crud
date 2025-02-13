package com.webiti.crud.service;

import com.webiti.crud.dto.response.OrderStatResponse;
import com.webiti.crud.dto.response.ProductStatResponse;
import com.webiti.crud.dto.response.UserStatResponse;

public interface StatService {
    UserStatResponse totalUser();
    ProductStatResponse totalProduct();
    OrderStatResponse totalOrderGroupByStatus();
}
