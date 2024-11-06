package com.phamcongvinh.testusser.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductGetAllDTO {
    private Long id;
    private String product_name;
    private List<String> thumbnail;
    private Integer quantity;
    private Double priceSale;
    private Double pricebuy;
    private String description;
    private List<String> rocks;
    private List<String> sugar;
    // private List<String> rocklever;

}
