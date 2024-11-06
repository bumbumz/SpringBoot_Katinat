package com.phamcongvinh.testusser.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.phamcongvinh.testusser.dto.CartDetailDTO;
import com.phamcongvinh.testusser.dto.CartDetailGetDTO;
import com.phamcongvinh.testusser.enity.CartDetail;
import com.phamcongvinh.testusser.service.CartDetailSerivce;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@AllArgsConstructor
@RequestMapping("api/cartdetail")

public class CartDetailController {

    private final CartDetailSerivce cartDetailService;

    @GetMapping
    public ResponseEntity<List<CartDetailGetDTO>> index() {
        return ResponseEntity.ok(cartDetailService.index());
    }

    @PostMapping("/store")
    public ResponseEntity<CartDetail> store(@RequestBody CartDetailDTO cartDetailDTO) {
        return ResponseEntity.ok(cartDetailService.store(cartDetailDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<CartDetailGetDTO>> geiId(@PathVariable Long id) {
        return ResponseEntity.ok(cartDetailService.geiId(id));
    }

    @DeleteMapping("/destroy/{id}")
    public ResponseEntity<String> destroy(@PathVariable Long id) {
        CartDetail cart = cartDetailService.show(id);
        if (cart == null) {
            return ResponseEntity.notFound().build();
        }
        cartDetailService.destroy(id);
        return ResponseEntity.ok("Xóa thành công");

    }

    @PostMapping("/cong/{id}")
    public ResponseEntity<String> Cong(@PathVariable Long id) {
        CartDetail cart = cartDetailService.show(id);
        if (cart == null) {
            return ResponseEntity.notFound().build();
        }
        cartDetailService.CongQuantity(id);
        return ResponseEntity.ok("Cộng thành công");

    }

    @PostMapping("/tru/{id}")
    public ResponseEntity<String> Tru(@PathVariable Long id) {
        CartDetail cart = cartDetailService.show(id);
        if (cart == null) {
            return ResponseEntity.notFound().build();
        }
        cartDetailService.TruQuantity(id);
        return ResponseEntity.ok("Trừ thành công");

    }

    @DeleteMapping("/deleteall/{userId}")
    public ResponseEntity<String> deleteall(@PathVariable Long userId) {
        cartDetailService.destroyAllByUserId(userId);
        return ResponseEntity.ok("Xóa tất cả");
    }

}
