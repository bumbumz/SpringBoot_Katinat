package com.phamcongvinh.testusser.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.experimental.FieldDefaults;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
// khởi tạo tham số

public class ProductDTO {

    private String name;
    private String slug;
    private String content;
    private String description;
    private Double pricebuy;
    private Integer status;
    private Integer quantity;
    private Long categoryId;

    List<String> thumbnail;

}
