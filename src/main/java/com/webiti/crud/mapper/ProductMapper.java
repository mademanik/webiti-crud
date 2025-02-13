package com.webiti.crud.mapper;

import com.webiti.crud.dto.request.ProductRequest;
import com.webiti.crud.dto.response.ProductResponse;
import com.webiti.crud.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product mapToProduct(ProductRequest productRequest);
    ProductResponse mapToProductResponse(Product product);
    void mapUpdateProduct(ProductRequest productRequest, @MappingTarget Product product);
}
