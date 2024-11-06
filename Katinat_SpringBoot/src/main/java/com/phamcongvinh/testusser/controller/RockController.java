package com.phamcongvinh.testusser.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.phamcongvinh.testusser.enity.Product;
import com.phamcongvinh.testusser.enity.Rock;
import com.phamcongvinh.testusser.repository.RockRepository;
import com.phamcongvinh.testusser.service.RockService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequestMapping("api/rock")
@AllArgsConstructor
public class RockController {
    @Autowired
    private final RockService rockService;

    @Autowired
    private final  RockRepository rockRepository;
    @PostMapping("/store")
    public ResponseEntity<Product> store(@RequestParam("id") Long id, @RequestParam("name") String name) {
        return ResponseEntity.ok(rockService.store(id, name));

    }

    @GetMapping("/product/{productId}")
public ResponseEntity<List<Rock>> getRocksByProductId(@PathVariable Long productId) {
    List<Rock> rocks = rockRepository.findByProductId(productId);
    if (rocks.isEmpty()) {
        return ResponseEntity.noContent().build();
    }
    return ResponseEntity.ok(rocks);
}

}
