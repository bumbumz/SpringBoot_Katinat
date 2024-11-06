package com.phamcongvinh.testusser.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.phamcongvinh.testusser.dto.WishlistProUseDTO;
import com.phamcongvinh.testusser.enity.Wishlist;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Long> {
    List<Wishlist> findByUser_UserId(Long userId);

    Optional<Wishlist> findByUser_UserIdAndProduct_Id(Long userId, Long productId);
}
