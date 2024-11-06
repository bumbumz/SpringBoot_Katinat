package com.phamcongvinh.testusser.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.phamcongvinh.testusser.enity.OrderDetail;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
    // Bạn có thể thêm các phương thức tùy chỉnh nếu cần
}