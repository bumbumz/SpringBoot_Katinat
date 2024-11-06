package com.phamcongvinh.testusser.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.phamcongvinh.testusser.enity.Product_Sugar;

@Repository
public interface ProductSugerRepository extends JpaRepository<Product_Sugar,Long> {
    
}
