package com.phamcongvinh.testusser.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.phamcongvinh.testusser.enity.ProductImage;

@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage,Long> {
    
}
