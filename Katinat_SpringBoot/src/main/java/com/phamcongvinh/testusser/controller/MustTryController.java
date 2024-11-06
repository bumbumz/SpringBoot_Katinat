package com.phamcongvinh.testusser.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.phamcongvinh.testusser.dto.MustTryProductDTO;
import com.phamcongvinh.testusser.dto.MustTryProductGetDTO;
import com.phamcongvinh.testusser.enity.MustTryProduct;
import com.phamcongvinh.testusser.service.MustTryService;

import lombok.AllArgsConstructor;
import java.util.List;
@Controller
@RequestMapping("api/must-try")
@AllArgsConstructor
public class MustTryController {
    @Autowired
    private final MustTryService mustTryService;

    @PostMapping("/store")
    public ResponseEntity<MustTryProduct> store(
            @RequestParam("product_id") Long id) {
        MustTryProductDTO mustTryProductDTO = new MustTryProductDTO( id);
        return ResponseEntity.ok(mustTryService.store(mustTryProductDTO));

    }

    @GetMapping
    public ResponseEntity<List<MustTryProductGetDTO>> getAll() {
        return ResponseEntity.ok(mustTryService.index());
    }

}
