package com.phamcongvinh.testusser.enity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "order_detail")
@Getter
@Setter
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "order_id", nullable = false)
    private Order order; // Thay Order bằng tên class entity đơn hàng của bạn

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product; // Liên kết với sản phẩm

    @Column(nullable = false)
    private Integer quantity; // Số lượng sản phẩm

    @Column(nullable = false)
    private Double price; // Giá sản phẩm tại thời điểm đặt hàng

    @Column(nullable = false)
    private LocalDateTime createdAt; // Ngày tạo

    // Bạn có thể thêm các thuộc tính khác nếu cần
}
