package com.phamcongvinh.testusser.dto;


import lombok.Data;
import java.util.List;



@Data
public class OrderConfirmationRequest {
    private String userEmail;
    private String orderCode;
    private double total;
    private String customerName;
    private String customerAddress;
    private String customerPhone;
    private List<OrderDetailDTO> orderDetails;
}

