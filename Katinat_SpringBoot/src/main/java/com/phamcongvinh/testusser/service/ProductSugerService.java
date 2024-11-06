package com.phamcongvinh.testusser.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phamcongvinh.testusser.dto.ProductSugarDTO;
import com.phamcongvinh.testusser.enity.Product;
import com.phamcongvinh.testusser.enity.Product_Sugar;
import com.phamcongvinh.testusser.enity.Sugar;
import com.phamcongvinh.testusser.repository.ProductRepository;
import com.phamcongvinh.testusser.repository.ProductSugerRepository;
import com.phamcongvinh.testusser.repository.SugarRepository;

import java.util.List;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductSugerService {
    @Autowired
    private final ProductSugerRepository productSugerRepository;
    
    @Autowired
    private final ProductRepository productRepository;

    @Autowired
    private final SugarRepository sugarRepository;

    public Product_Sugar store(ProductSugarDTO productSugarDTO)
    {
        Optional<Product> product=productRepository.findById(productSugarDTO.getProduct_id());
        Optional<Sugar> sugar= sugarRepository.findById(productSugarDTO.getSugar_id());
        if(product.isPresent() && sugar.isPresent())
        {
            Product_Sugar  productSugar = new Product_Sugar();
            Product products=product.get();
            Sugar sugars=sugar.get();
            productSugar.setProduct(products);
            productSugar.setSugar(sugars);
            productSugar.setName(productSugarDTO.getName());
            productSugerRepository.save(productSugar);
            return productSugar;

        }
         throw new EntityNotFoundException("Product không tồn tại");
    }

    public List<Product_Sugar> index()
    {
        List<Product_Sugar> product_Sugar=productSugerRepository.findAll();
        return product_Sugar;
    }
    
}
