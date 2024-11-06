package com.phamcongvinh.testusser.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.phamcongvinh.testusser.dto.ProductSaleDTO;
import com.phamcongvinh.testusser.dto.ProductSaleGetAllDTO;
import com.phamcongvinh.testusser.enity.ProductSale;
import com.phamcongvinh.testusser.service.ProductSaleService;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;


@Controller
@RequestMapping("api/product-sale")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductSaleController {
    ProductSaleService  productSaleService;
    @PostMapping("/update/{id}")
    public ResponseEntity<ProductSale> updateProductSale(@PathVariable("id") Long id,
    @RequestParam ("price") Double price)
    {
        ProductSaleDTO  productSaleDTO = new ProductSaleDTO(price);

        ProductSale productSale = productSaleService.update(id, productSaleDTO);
        return ResponseEntity.ok(productSale);
        
    }
    @GetMapping
    public ResponseEntity<List<ProductSaleGetAllDTO>> index()
    {
        return ResponseEntity.ok(productSaleService.index());
    }


    
}
