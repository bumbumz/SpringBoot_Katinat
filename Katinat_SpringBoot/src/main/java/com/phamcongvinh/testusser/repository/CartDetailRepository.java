package com.phamcongvinh.testusser.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.phamcongvinh.testusser.enity.CartDetail;
import com.phamcongvinh.testusser.enity.Product;
import com.phamcongvinh.testusser.enity.User;

@Repository
public interface CartDetailRepository extends JpaRepository<CartDetail, Long> {
    List<CartDetail> findByUser_UserId(Long userId);
    @Query("SELECT cd FROM CartDetail cd WHERE cd.product = ?1 AND cd.user = ?2 AND cd.sugar = ?3 AND cd.rock = ?4")
    CartDetail findByProductAndUserAndSugarAndRock(Product product, User user, String sugar, String rock);
}
