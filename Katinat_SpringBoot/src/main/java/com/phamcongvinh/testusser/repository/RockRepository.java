package com.phamcongvinh.testusser.repository;

import java.util.List;
// import java.util.Optional;
// import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.phamcongvinh.testusser.enity.Rock;

@Repository
public interface RockRepository extends JpaRepository<Rock, Long> {
    List<Rock> findByProductId(Long productId);

}
