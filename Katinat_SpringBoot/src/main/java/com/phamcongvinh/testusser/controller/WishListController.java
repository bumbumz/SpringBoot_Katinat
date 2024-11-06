package com.phamcongvinh.testusser.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.phamcongvinh.testusser.dto.CartDetailDTO;
import com.phamcongvinh.testusser.dto.CartDetailGetDTO;
import com.phamcongvinh.testusser.dto.WishlistDTO;
import com.phamcongvinh.testusser.dto.WishlistGetAllDTO;
import com.phamcongvinh.testusser.dto.WishlistProUseDTO;
import com.phamcongvinh.testusser.enity.CartDetail;
import com.phamcongvinh.testusser.enity.Wishlist;
import com.phamcongvinh.testusser.service.WishlistService;

import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequestMapping("api/wishlist")
@AllArgsConstructor
public class WishListController {

    @Autowired
    private final WishlistService wishListService;

    @GetMapping
    public ResponseEntity<List<WishlistGetAllDTO>> index() {
        return ResponseEntity.ok(wishListService.index());
    }

    @PostMapping("/store")
    public ResponseEntity<Wishlist> store(
            @RequestBody WishlistDTO cartDetailDTO) {

        return ResponseEntity.ok(wishListService.store(cartDetailDTO));

    }

    @GetMapping("/{id}")
    public ResponseEntity<List<WishlistGetAllDTO>> geiId(@PathVariable Long id) {
        return ResponseEntity.ok(wishListService.geiId(id));
    }

    @GetMapping("/{pro}/{user}")
    public ResponseEntity<WishlistProUseDTO> geiProUse(@PathVariable Long pro, @PathVariable Long user) {
        return ResponseEntity.ok(wishListService.geiProUse(pro, user));
    }

    @DeleteMapping("/destroy/{pro}/{user}")
    public ResponseEntity<String> destroy(@PathVariable Long pro, @PathVariable Long user) {

        wishListService.destroy(pro, user);
        return ResponseEntity.ok("Xóa thành công");

    }

    @DeleteMapping("/detroyall/{id}")
    public ResponseEntity<String> detroyall(@PathVariable Long id) {
        wishListService.destroyAllByUserId(id);
        return ResponseEntity.ok("Xóa thành công");
    }

}
