package com.phamcongvinh.testusser.dto;

import java.util.List;

import com.phamcongvinh.testusser.enity.Product;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor 

public class ProductSaleGetAllDTO {
    private Long id;
    private Double priceSale;
    private String product_name;
    private Double pricebuy;
    private String description;
    private List<String> rocks;
    private List<String> sugar;
    private List<String> thumbnail;



    
}
