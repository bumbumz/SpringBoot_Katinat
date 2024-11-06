package com.phamcongvinh.testusser.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.phamcongvinh.testusser.dto.ProductSugarDTO;
import com.phamcongvinh.testusser.enity.Product_Sugar;
import com.phamcongvinh.testusser.service.ProductSugerService;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("api/product-sugar")
@AllArgsConstructor
public class ProductSugarController {
    @Autowired
    private final ProductSugerService productSugarService;

    @PostMapping("/store")
    public ResponseEntity<Product_Sugar>store(
        @RequestParam("name") String name,
        @RequestParam("product_id") Long product_id,
        @RequestParam("sugar_id") Long sugar_id
    )
    {
        ProductSugarDTO  productSugarDTO = new ProductSugarDTO(product_id,sugar_id,name);
        return ResponseEntity.ok( productSugarService.store(productSugarDTO));
        
    }

    @GetMapping("")
    public ResponseEntity<List<Product_Sugar>> index()
    {
        return ResponseEntity.ok(productSugarService.index());
        
    }
   
    
    
}
