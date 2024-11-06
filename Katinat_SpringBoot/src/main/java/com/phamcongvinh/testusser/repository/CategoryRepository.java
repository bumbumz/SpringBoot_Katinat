package com.phamcongvinh.testusser.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.stereotype.Repository;

import com.phamcongvinh.testusser.enity.Category;



public interface CategoryRepository extends JpaRepository<Category,Long> {
    List<Category> findByStatusIn(List<Integer> statusList);
    List<Category>  findByStatus(Integer status);

    
}
