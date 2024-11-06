package com.phamcongvinh.testusser.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phamcongvinh.testusser.enity.Sugar;
import com.phamcongvinh.testusser.repository.SugarRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SugarService {
    @Autowired
    private final SugarRepository sugarRepository;

    public Sugar store(String name)
    {
        Sugar sugar = new Sugar();
        sugar.setName(name);
        sugarRepository.save(sugar);
        return sugar;

    }
    
}
