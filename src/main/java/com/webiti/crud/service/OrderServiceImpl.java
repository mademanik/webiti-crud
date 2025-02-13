package com.webiti.crud.service;

import com.webiti.crud.dto.request.OrderRequest;
import com.webiti.crud.dto.response.OrderResponse;
import com.webiti.crud.helper.ValidationErrors;
import com.webiti.crud.helper.Validations;
import com.webiti.crud.mapper.OrderMapper;
import com.webiti.crud.mapper.ProductMapper;
import com.webiti.crud.mapper.UserMapper;
import com.webiti.crud.model.Order;
import com.webiti.crud.model.Product;
import com.webiti.crud.model.User;
import com.webiti.crud.repository.OrderRepository;
import com.webiti.crud.repository.ProductRepository;
import com.webiti.crud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<OrderResponse> allOrders() {
        List<Order> orders  = orderRepository.findAll();
        return orders.stream()
                .map(order -> OrderResponse.builder()
                        .id(order.getId())
                        .user(userMapper.mapToUserResponse(order.getUser()))
                        .product(productMapper.mapToProductResponse(order.getProduct()))
                        .quantity(order.getQuantity())
                        .totalPrice(order.getTotalPrice())
                        .status(order.getStatus())
                        .createdAt(order.getCreatedAt())
                        .updatedAt(order.getUpdatedAt())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderResponse> allOrderByUserId(Integer userId) {
        List<Order> orders  = orderRepository.findByUserId(userId);
        return orders.stream()
                .map(order -> OrderResponse.builder()
                        .id(order.getId())
                        .user(userMapper.mapToUserResponse(order.getUser()))
                        .product(productMapper.mapToProductResponse(order.getProduct()))
                        .quantity(order.getQuantity())
                        .totalPrice(order.getTotalPrice())
                        .status(order.getStatus())
                        .createdAt(order.getCreatedAt())
                        .updatedAt(order.getUpdatedAt())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public OrderResponse createOrder(OrderRequest input) {

        Optional<User> optOfUser = userRepository.findById(input.getUser_id());
        Validations.checkArgument(
                optOfUser.isPresent(), ValidationErrors.USER_NOT_FOUND);

        Optional<Product> optOfProduct = productRepository.findById(input.getProduct_id());
        Validations.checkArgument(
                optOfProduct.isPresent(), ValidationErrors.PRODUCT_NOT_FOUND);

        Order order = Order.builder()
                .user(optOfUser.get())
                .product(optOfProduct.get())
                .quantity(input.getQuantity())
                .totalPrice(input.getTotalPrice())
                .status(input.getStatus())
                .build();

        orderRepository.save(order);

        return OrderResponse.builder()
                .id(order.getId())
                .user(userMapper.mapToUserResponse(order.getUser()))
                .product(productMapper.mapToProductResponse(order.getProduct()))
                .quantity(order.getQuantity())
                .totalPrice(order.getTotalPrice())
                .status(order.getStatus())
                .createdAt(order.getCreatedAt())
                .updatedAt(order.getUpdatedAt())
                .build();
    }

    @Override
    public OrderResponse updateOrderById(Long id, OrderRequest input) {
        Optional<User> optOfUser = userRepository.findById(input.getUser_id());
        Validations.checkArgument(
                optOfUser.isPresent(), ValidationErrors.USER_NOT_FOUND);

        Optional<Product> optOfProduct = productRepository.findById(input.getProduct_id());
        Validations.checkArgument(
                optOfProduct.isPresent(), ValidationErrors.PRODUCT_NOT_FOUND);

        Optional<Order> optOfOrder = orderRepository.findById(id.intValue());
        Validations.checkArgument(
                optOfOrder.isPresent(), ValidationErrors.ORDER_NOT_FOUND);

        Order order = optOfOrder.get();
        order.setUser(optOfUser.get());
        order.setProduct(optOfProduct.get());
        order.setQuantity(input.getQuantity());
        order.setTotalPrice(input.getTotalPrice());
        order.setStatus(input.getStatus());
        orderRepository.save(order);

        return OrderResponse.builder()
                .id(order.getId())
                .user(userMapper.mapToUserResponse(order.getUser()))
                .product(productMapper.mapToProductResponse(order.getProduct()))
                .quantity(order.getQuantity())
                .totalPrice(order.getTotalPrice())
                .status(order.getStatus())
                .createdAt(order.getCreatedAt())
                .updatedAt(order.getUpdatedAt())
                .build();
    }

    @Override
    public OrderResponse getOrderById(Long id) {
        Order order = orderRepository.findById(id.intValue()).orElse(null);

        return OrderResponse.builder()
                .id(order.getId())
                .user(userMapper.mapToUserResponse(order.getUser()))
                .product(productMapper.mapToProductResponse(order.getProduct()))
                .quantity(order.getQuantity())
                .totalPrice(order.getTotalPrice())
                .status(order.getStatus())
                .createdAt(order.getCreatedAt())
                .updatedAt(order.getUpdatedAt())
                .build();
    }

    @Override
    public void deleteOrderById(Long id) {
        Optional<Order> optOfOrder = orderRepository.findById(id.intValue());
        Validations.checkArgument(
                optOfOrder.isPresent(), ValidationErrors.ORDER_NOT_FOUND);
        Order order = optOfOrder.get();
        orderRepository.deleteById(order.getId().intValue());
    }
}
