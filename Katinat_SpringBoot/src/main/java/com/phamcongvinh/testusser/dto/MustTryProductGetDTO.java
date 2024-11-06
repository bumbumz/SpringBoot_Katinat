package com.phamcongvinh.testusser.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MustTryProductGetDTO {
    private Long id;
    private Long product_id;
    private String product_name;
    private Double priceSale;
    private Double pricebuy;
    private String description;
     private List<String> rocks;
    private List<String> sugar;

    private List<String> thumbnail;

}
