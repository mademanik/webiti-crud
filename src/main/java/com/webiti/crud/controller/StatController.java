package com.webiti.crud.controller;

import com.webiti.crud.dto.response.OrderStatResponse;
import com.webiti.crud.dto.response.ProductStatResponse;
import com.webiti.crud.dto.response.UserStatResponse;
import com.webiti.crud.helper.ApplicationMessages;
import com.webiti.crud.helper.ResponseHandler;
import com.webiti.crud.service.StatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/stats")
@RestController
public class StatController {

    @Autowired
    private StatService statService;

    @GetMapping("/users")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Object> userStatistics() {
        UserStatResponse response = statService.totalUser();
        return ResponseHandler.generateResponse(ApplicationMessages.USER_STAT_RETRIEVED.getMessage(),
                HttpStatus.OK, response);
    }

    @GetMapping("/products")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Object> productStatistics() {
        ProductStatResponse response = statService.totalProduct();
        return ResponseHandler.generateResponse(ApplicationMessages.PRODUCT_STAT_RETRIEVED.getMessage(),
                HttpStatus.OK, response);
    }

    @GetMapping("/orders")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Object> orderStatistics() {
        OrderStatResponse response = statService.totalOrderGroupByStatus();
        return ResponseHandler.generateResponse(ApplicationMessages.ORDER_STAT_RETRIEVED.getMessage(),
                HttpStatus.OK, response);
    }

}
