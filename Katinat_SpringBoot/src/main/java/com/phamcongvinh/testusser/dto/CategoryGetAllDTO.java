package com.phamcongvinh.testusser.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CategoryGetAllDTO {
    private String id;
    private String name;
    private Integer status;
    private  String img;
    private List<ProductGetAllDTO> product;



    
}
