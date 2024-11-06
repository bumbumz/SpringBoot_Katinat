package com.phamcongvinh.testusser.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.phamcongvinh.testusser.enity.Sugar;

@Repository
public interface SugarRepository extends JpaRepository<Sugar,Long>{
    
}
