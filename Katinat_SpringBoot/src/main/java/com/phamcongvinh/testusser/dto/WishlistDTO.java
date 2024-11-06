package com.phamcongvinh.testusser.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WishlistDTO {
    private Long productid;
    private Long userid;
    private Long quantity;
    private String sugar;
    private String rock;
    private String note;

}
