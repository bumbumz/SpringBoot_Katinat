package com.phamcongvinh.testusser.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.phamcongvinh.testusser.enity.ProductStore;

@Repository
public interface ProductStoreRepository extends  JpaRepository<ProductStore, Long> {

    
}
