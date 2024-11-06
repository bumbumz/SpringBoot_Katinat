package com.phamcongvinh.testusser.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.phamcongvinh.testusser.enity.NewsEven;
@Repository
public interface NewsEvenRepository extends JpaRepository<NewsEven, Long> {

}
