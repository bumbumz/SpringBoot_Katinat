package com.phamcongvinh.testusser.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NewsEvenDTO {
    private String name;
    private String thumbnail;
    private String image;
    private String content;
    
}
