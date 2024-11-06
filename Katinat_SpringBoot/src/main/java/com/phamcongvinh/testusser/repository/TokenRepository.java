package com.phamcongvinh.testusser.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.phamcongvinh.testusser.enity.Token;



public interface TokenRepository extends JpaRepository<Token, Integer> {
}