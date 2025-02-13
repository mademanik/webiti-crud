package com.webiti.crud.service;

import com.webiti.crud.dto.request.ProductRequest;
import com.webiti.crud.dto.response.ProductResponse;
import com.webiti.crud.helper.ValidationErrors;
import com.webiti.crud.helper.Validations;
import com.webiti.crud.mapper.ProductMapper;
import com.webiti.crud.model.Product;
import com.webiti.crud.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<ProductResponse> allProducts(String name, Double minPrice, Double maxPrice) {
        List<Product> products  = productRepository.findByFilters(name, minPrice, maxPrice);
        return products.stream()
                .map(product -> productMapper.mapToProductResponse(product))
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponse createProduct(ProductRequest input) {
        Product product = Product.builder()
                .name(input.getName())
                .price(input.getPrice())
                .build();

        productRepository.save(product);
        return productMapper.mapToProductResponse(product);
    }

    @Override
    public ProductResponse updateProductById(Long id, ProductRequest input) {
        Optional<Product> optOfProduct = productRepository.findById(id.intValue());
        Validations.checkArgument(
                optOfProduct.isPresent(), ValidationErrors.PRODUCT_NOT_FOUND);
        Product product = optOfProduct.get();
        product.setName(input.getName());
        product.setPrice(input.getPrice());
        productRepository.save(product);
        return productMapper.mapToProductResponse(product);
    }

    @Override
    public ProductResponse getProductById(Long id) {
        Product product = productRepository.findById(id.intValue()).orElse(null);
        return productMapper.mapToProductResponse(product);
    }

    @Override
    public void deleteProductById(Long id) {
        Optional<Product> optOfProduct = productRepository.findById(id.intValue());
        Validations.checkArgument(
                optOfProduct.isPresent(), ValidationErrors.PRODUCT_NOT_FOUND);
        Product product = optOfProduct.get();
        productRepository.deleteById(product.getId().intValue());
    }
}
