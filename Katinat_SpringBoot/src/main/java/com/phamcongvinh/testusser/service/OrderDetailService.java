package com.phamcongvinh.testusser.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phamcongvinh.testusser.dto.OrderDetailDTO;
import com.phamcongvinh.testusser.enity.Order;
import com.phamcongvinh.testusser.enity.OrderDetail;
import com.phamcongvinh.testusser.enity.Product;
import com.phamcongvinh.testusser.repository.OrderDetailRepository;
import com.phamcongvinh.testusser.repository.OrderRepository;
import com.phamcongvinh.testusser.repository.ProductRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderDetailService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    public List<OrderDetail> createOrderDetails(OrderDetailDTO orderDetailDTO) {
        Order order = orderRepository.findById(orderDetailDTO.getOderId())
                .orElseThrow(() -> new RuntimeException("Order not found"));
    
        List<OrderDetail> orderDetails = new ArrayList<>();
    
        // Ensure the lists are of the same size
        if (orderDetailDTO.getProductId().size() != orderDetailDTO.getQuantity().size() ||
            orderDetailDTO.getProductId().size() != orderDetailDTO.getQuantity().size()) {
            throw new IllegalArgumentException("Product, quantity, and price lists must be of the same size");
        }
    
        // Iterate through each product, quantity, and price in parallel
        for (int i = 0; i < orderDetailDTO.getProductId().size(); i++) {
            Long productId = orderDetailDTO.getProductId().get(i);
            Integer quantity = orderDetailDTO.getQuantity().get(i);
            Double price = orderDetailDTO.getPrice().get(i);
    
            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new RuntimeException("Product not found"));
    
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrder(order);
            orderDetail.setProduct(product);
            orderDetail.setQuantity(quantity);
            orderDetail.setPrice(price);
            orderDetail.setCreatedAt(LocalDateTime.now());
    
            orderDetails.add(orderDetail);
        }
    
        return orderDetailRepository.saveAll(orderDetails);
    }

    public List<OrderDetail> getAllOrderDetails() {
        return orderDetailRepository.findAll();
    }

    public OrderDetail getOrderDetailById(Long id) {
        return orderDetailRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("OrderDetail not found"));
    }

    public void deleteOrderDetail(Long id) {
        orderDetailRepository.deleteById(id);
    }
}
