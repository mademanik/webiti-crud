package com.webiti.crud.controller;

import com.webiti.crud.dto.request.OrderRequest;
import com.webiti.crud.dto.response.OrderResponse;
import com.webiti.crud.helper.ApplicationMessages;
import com.webiti.crud.helper.ResponseHandler;
import com.webiti.crud.model.RoleEnum;
import com.webiti.crud.model.User;
import com.webiti.crud.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/orders")
@RestController
@CrossOrigin
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("")
    public ResponseEntity<Object> createOrder(@RequestBody OrderRequest orderRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User userPrincipal = (User) authentication.getPrincipal();
        RoleEnum userRole = userPrincipal.getRole().getName();

        if (!orderRequest.getUser_id().equals(userPrincipal.getId()) && !userRole.equals(RoleEnum.ADMIN)) {
            return ResponseHandler.generateResponse(ApplicationMessages.UNAUTHORIZED_CREATE_ORDER.getMessage(),
                    HttpStatus.FORBIDDEN, null);
        }

        OrderResponse response = orderService.createOrder(orderRequest);
        return ResponseHandler.generateResponse(ApplicationMessages.ORDER_CREATED.getMessage(),
                HttpStatus.CREATED, response);
    }

    @GetMapping
    public ResponseEntity<Object> allOrders() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User userPrincipal = (User) authentication.getPrincipal();
        RoleEnum userRole = userPrincipal.getRole().getName();

        List<OrderResponse> response;

        if (userRole.equals(RoleEnum.ADMIN)) {
            response = orderService.allOrders();
        } else {
            response = orderService.allOrderByUserId(userPrincipal.getId());
        }
        return ResponseHandler.generateResponse(ApplicationMessages.ORDER_RETRIEVED.getMessage(),
                HttpStatus.OK, response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOrderById(@PathVariable("id") Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User userPrincipal = (User) authentication.getPrincipal();
        RoleEnum userRole = userPrincipal.getRole().getName();

        OrderResponse response = orderService.getOrderById(id);

        if (response != null && !response.getUser().getId().equals(userPrincipal.getId()) && !userRole.equals(RoleEnum.ADMIN)) {
            return ResponseHandler.generateResponse(ApplicationMessages.UNAUTHORIZED_GET_ORDER.getMessage(),
                    HttpStatus.FORBIDDEN, null);
        }

        if (response != null) {
            return ResponseHandler.generateResponse(ApplicationMessages.ORDER_RETRIEVED.getMessage(), HttpStatus.OK, response);
        } else {
            return ResponseHandler.generateResponse(ApplicationMessages.ORDER_NOT_FOUND
                    .getValue(id.toString()), HttpStatus.NOT_FOUND, null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteOrderById(@PathVariable("id") Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User userPrincipal = (User) authentication.getPrincipal();
        RoleEnum userRole = userPrincipal.getRole().getName();

        OrderResponse orderData = orderService.getOrderById(id);

        if (orderData != null && !orderData.getUser().getId().equals(userPrincipal.getId()) && !userRole.equals(RoleEnum.ADMIN)) {
            return ResponseHandler.generateResponse(ApplicationMessages.UNAUTHORIZED_DELETE_ORDER.getMessage(),
                    HttpStatus.FORBIDDEN, null);
        }

        orderService.deleteOrderById(id);
        return ResponseHandler.generateResponse(ApplicationMessages.ORDER_DELETED.getMessage(), HttpStatus.OK, null);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateOrder(@PathVariable("id") Long id, @RequestBody OrderRequest orderRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User userPrincipal = (User) authentication.getPrincipal();
        RoleEnum userRole = userPrincipal.getRole().getName();

        OrderResponse orderData = orderService.getOrderById(id);

        if (orderData != null && !orderData.getUser().getId().equals(userPrincipal.getId()) && !userRole.equals(RoleEnum.ADMIN)) {
            return ResponseHandler.generateResponse(ApplicationMessages.UNAUTHORIZED_DELETE_ORDER.getMessage(),
                    HttpStatus.FORBIDDEN, null);
        }

        OrderResponse response = orderService.updateOrderById(id, orderRequest);
        return ResponseHandler.generateResponse(ApplicationMessages.PRODUCT_UPDATED.getMessage(), HttpStatus.OK, response);
    }

}
