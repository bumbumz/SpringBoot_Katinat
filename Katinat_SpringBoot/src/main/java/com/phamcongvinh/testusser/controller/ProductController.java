package com.phamcongvinh.testusser.controller;

import java.util.ArrayList;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import com.phamcongvinh.testusser.dto.ProductDTO;
import com.phamcongvinh.testusser.dto.ProductGetAllDTO;
import com.phamcongvinh.testusser.enity.Product;
import com.phamcongvinh.testusser.service.ProductSevice;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("api/product")
public class ProductController {
    FileController fileController;
    ProductSevice productSevice;

    // ResponseEntity, bạn có thể tùy chỉnh mã trạng thái HTTP
    // để phản ánh chính xác tình trạng của phản hồi, chẳng hạn như:
    // 200 OK: Thành công.
    // 201 CREATED: Đã tạo mới thành công.
    // 404 NOT FOUND: Không tìm thấy tài nguyên.
    // 400 BAD REQUEST: Yêu cầu không hợp lệ.
    @GetMapping
    public ResponseEntity<List<ProductGetAllDTO>> index() {
        return ResponseEntity.ok(productSevice.index());
    }

    @GetMapping("/getall")
    public ResponseEntity<List<Product>> getall() {
        return ResponseEntity.ok(productSevice.getAllProducts());
    }

    @PostMapping("/store")
    public ResponseEntity<Product> store(
            @RequestParam("name") String name,
            @RequestParam("slug") String slug,
            @RequestParam("content") String content,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam("price") Double pricebuy,
            @RequestParam("category_id") Long category_id,

            @RequestParam("status") Integer status,
            @RequestParam("qty") Integer quantity,
            @RequestParam(value = "thumbnail", required = false) List<MultipartFile> files) {

        List<String> imagePaths = new ArrayList<>();
        if (files != null && !files.isEmpty()) {
            for (MultipartFile file : files) {
                String imagePath = fileController.uploadFile(file);
                imagePaths.add(imagePath);
            }
        }
        ProductDTO productDTO = new ProductDTO(
                name,
                slug,
                content,
                description,
                pricebuy,
                status,
                quantity,
                category_id,
                imagePaths);

        return ResponseEntity.ok(productSevice.store(productDTO));
    }

    @PostMapping("update-status/{id}")
    public ResponseEntity<Product> updateStatus(@PathVariable("id") Long id) {
        return ResponseEntity.ok(productSevice.updateStatus(id));
    }

    @PostMapping("update-trash/{id}")
    public ResponseEntity<Product> updateTrash(@PathVariable("id") Long id) {
        return ResponseEntity.ok(productSevice.updateTrash(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductGetAllDTO>> searchProducts(@RequestParam String query) {
        List<ProductGetAllDTO> products = productSevice.search(query);
        return ResponseEntity.ok(products);
    }

}
