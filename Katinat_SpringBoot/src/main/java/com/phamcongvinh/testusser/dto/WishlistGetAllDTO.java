package com.phamcongvinh.testusser.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WishlistGetAllDTO {
    private Long id;
    private Long product_id;
    private String product_name;
    private Double pricebuy;
    private List<String> thumbnail;
    private Long user_id;
    private String user_name;
    private Long quantity;
    private String sugar;
    private String rock;
    private String note;
}
