package com.phamcongvinh.testusser.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.phamcongvinh.testusser.enity.MustTryProduct;

@Repository
public interface MustTryRepository extends JpaRepository<MustTryProduct,Long> {

    
}
