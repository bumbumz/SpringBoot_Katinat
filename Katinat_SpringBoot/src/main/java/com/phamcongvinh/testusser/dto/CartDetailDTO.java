package com.phamcongvinh.testusser.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CartDetailDTO {

    private Long productid;
    private Long userid;
    private Long quantity;
    private String sugar;
    private String rock;
    private String note;

}
