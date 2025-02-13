package com.webiti.crud.service;

import com.webiti.crud.dto.response.*;
import com.webiti.crud.mapper.ProductMapper;
import com.webiti.crud.mapper.UserMapper;
import com.webiti.crud.model.Order;
import com.webiti.crud.model.OrderStatus;
import com.webiti.crud.model.Product;
import com.webiti.crud.model.User;
import com.webiti.crud.repository.OrderRepository;
import com.webiti.crud.repository.ProductRepository;
import com.webiti.crud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StatServiceImpl implements StatService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public UserStatResponse totalUser() {
        List<User> users = userRepository.findAll();
        List<UserResponse> userResponses = users.stream().map(user ->
                userMapper.mapToUserResponse(user)).toList();
        return UserStatResponse.builder()
                .totalUser(users.size())
                .dataUsers(userResponses)
                .build();
    }

    @Override
    public ProductStatResponse totalProduct() {
        List<Product> products = productRepository.findAll();
        List<ProductResponse> productResponses = products.stream().map(product ->
                productMapper.mapToProductResponse(product)).toList();

        return ProductStatResponse.builder()
                .totalProduct(products.size())
                .dataProducts(productResponses)
                .build();
    }

    @Override
    public OrderStatResponse totalOrderGroupByStatus() {
        List<Order> orders = orderRepository.findAll();
        Integer totalOrders = orders.size();

        Map<OrderStatus, Long> statusCount = orders.stream()
                .collect(Collectors.groupingBy(Order::getStatus, Collectors.counting()));

        return OrderStatResponse.builder()
                .totalOrders(totalOrders)
                .statuses(statusCount)
                .build();
    }
}
