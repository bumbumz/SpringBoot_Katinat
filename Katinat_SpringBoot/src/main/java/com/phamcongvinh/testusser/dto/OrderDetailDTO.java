package com.phamcongvinh.testusser.dto;

import java.util.List;

import com.phamcongvinh.testusser.enity.Order;
import com.phamcongvinh.testusser.enity.Product;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderDetailDTO {
    private Long oderId;
    private List<Long> productId;
    private List<Integer> quantity;
    private List<Double> price;

}
