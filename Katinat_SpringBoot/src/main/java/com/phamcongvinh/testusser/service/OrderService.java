package com.phamcongvinh.testusser.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phamcongvinh.testusser.enity.Order;
import com.phamcongvinh.testusser.enity.User;
import com.phamcongvinh.testusser.repository.OrderRepository;
import com.phamcongvinh.testusser.repository.UserRepository;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    
    @Autowired
    private UserRepository  userRepository;


    public Order addOrder(Long userId) {

         User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Order  order = new Order();
        order.setOrderDate(LocalDateTime.now());
        order.setUser(user);

        return orderRepository.save(order);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}