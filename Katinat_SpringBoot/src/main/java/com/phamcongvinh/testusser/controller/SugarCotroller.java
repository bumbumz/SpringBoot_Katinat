package com.phamcongvinh.testusser.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.phamcongvinh.testusser.enity.Sugar;
import com.phamcongvinh.testusser.service.SugarService;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping("api/sugar")
@AllArgsConstructor
public class SugarCotroller {
    @Autowired
    private final SugarService sugarService;

    @PostMapping("/store")
    public ResponseEntity<Sugar> store(
            @RequestParam("name") String name) {
        return ResponseEntity.ok(sugarService.store(name));

    }

}
