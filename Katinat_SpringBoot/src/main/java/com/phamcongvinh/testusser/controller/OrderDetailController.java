package com.phamcongvinh.testusser.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.phamcongvinh.testusser.dto.OrderDetailDTO;
import com.phamcongvinh.testusser.enity.OrderDetail;
import com.phamcongvinh.testusser.service.OrderDetailService;

@RestController
@RequestMapping("/api/order-details")
public class OrderDetailController {
    @Autowired
    private OrderDetailService orderDetailService;

     @PostMapping
    public ResponseEntity<List<OrderDetail>> createOrderDetails(@RequestBody OrderDetailDTO orderDetailDTO) {
        List<OrderDetail> orderDetails = orderDetailService.createOrderDetails(orderDetailDTO);
        return ResponseEntity.ok(orderDetails);
    }

    // Các endpoint khác như xóa, cập nhật
}
