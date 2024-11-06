package com.phamcongvinh.testusser.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductSugarDTO {
    private Long product_id;
    private Long sugar_id;
    private String name;
}
