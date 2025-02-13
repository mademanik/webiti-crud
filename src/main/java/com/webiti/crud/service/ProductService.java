package com.webiti.crud.service;

import com.webiti.crud.dto.request.ProductRequest;
import com.webiti.crud.dto.response.ProductResponse;
import com.webiti.crud.model.Product;

import java.util.List;

public interface ProductService {
    List<ProductResponse> allProducts(String name, Double minPrice, Double maxPrice);
    ProductResponse createProduct(ProductRequest input);
    ProductResponse updateProductById(Long id, ProductRequest input);
    ProductResponse getProductById(Long id);
    void deleteProductById(Long id);
}
