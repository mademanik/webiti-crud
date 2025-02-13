package com.webiti.crud.dto.response;

import com.webiti.crud.model.Product;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ProductStatResponse {
    private Integer totalProduct;
    private List<ProductResponse> dataProducts;
}
