package com.webiti.crud.controller;

import com.webiti.crud.dto.request.ProductRequest;
import com.webiti.crud.dto.response.ProductResponse;
import com.webiti.crud.helper.ApplicationMessages;
import com.webiti.crud.helper.ResponseHandler;
import com.webiti.crud.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/products")
@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<Object> allProducts(@RequestParam(required = false) String name,
                                              @RequestParam(required = false) Double minPrice,
                                              @RequestParam(required = false) Double maxPrice) {
        List<ProductResponse> response = productService.allProducts(name, minPrice, maxPrice);
        return ResponseHandler.generateResponse(ApplicationMessages.PRODUCT_RETRIEVED.getMessage(),
                HttpStatus.OK, response);
    }

    @PostMapping("")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Object> createProduct(@RequestBody ProductRequest productRequest) {
        ProductResponse response = productService.createProduct(productRequest);
        return ResponseHandler.generateResponse(ApplicationMessages.PRODUCT_CREATED.getMessage(),
                HttpStatus.CREATED, response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getProductById(@PathVariable("id") Long id) {
        ProductResponse response = productService.getProductById(id);
        if (response != null) {
            return ResponseHandler.generateResponse(ApplicationMessages.PRODUCT_RETRIEVED.getMessage(), HttpStatus.OK, response);
        } else {
            return ResponseHandler.generateResponse(ApplicationMessages.PRODUCT_NOT_FOUND
                    .getValue(id.toString()), HttpStatus.NOT_FOUND, response);
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Object> deleteProductById(@PathVariable("id") Long id) {
        productService.deleteProductById(id);
        return ResponseHandler.generateResponse(ApplicationMessages.PRODUCT_DELETED.getMessage(), HttpStatus.OK, null);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Object> updateProduct(@PathVariable("id") Long id, @RequestBody ProductRequest productRequest) {
        ProductResponse response = productService.updateProductById(id, productRequest);
        return ResponseHandler.generateResponse(ApplicationMessages.PRODUCT_UPDATED.getMessage(), HttpStatus.OK, response);
    }
}
