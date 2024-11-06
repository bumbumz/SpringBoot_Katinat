package com.phamcongvinh.testusser.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.phamcongvinh.testusser.enity.ProductSale;

@Repository
public interface ProductSaleRepository extends JpaRepository<ProductSale,Long> {
    ProductSale findByProductId(Long id);
    List<ProductSale> findByStatusIn(Collection<Integer>  statusList);
}
