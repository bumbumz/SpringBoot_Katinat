package com.phamcongvinh.testusser.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.phamcongvinh.testusser.dto.CategoryGetAllDTO;
import com.phamcongvinh.testusser.dto.CateroryDTO;
import com.phamcongvinh.testusser.enity.Category;
import com.phamcongvinh.testusser.service.CategoryService;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
@RequestMapping("api/categories")
public class CategoryController {
    @Autowired
    private final CategoryService categoryService;

    @Autowired
    private final FileController fileController;

    @GetMapping("")
    public ResponseEntity<List<CategoryGetAllDTO>> index() {
        return ResponseEntity.ok(categoryService.index());

    }

    @PostMapping("/store")
    public ResponseEntity<Category> store(
            @RequestParam("name") String name,
            @RequestParam("status") Integer status,
            @RequestParam(value = "img", required = false) MultipartFile img

    ) {
        String imgPaths = fileController.uploadFile(img);
        CateroryDTO cateroryDTO = new CateroryDTO(name, imgPaths, status);
        return ResponseEntity.ok(categoryService.store(cateroryDTO));

    }

    @PostMapping("/updateall/{id}")
    public ResponseEntity<Category> update(@PathVariable Long id,
            @RequestParam("name") String name,
            @RequestParam(value = "img", required = false) MultipartFile img) {
        Category catedele = categoryService.show(id);
        String oldimg = catedele.getImg();
        String imgPaths = null;
        if (img != null) {
            imgPaths = fileController.uploadFile(img);
            if (oldimg != null) {
                fileController.deleteFile(oldimg);
            }

        } else {
            imgPaths = oldimg;
        }
        CateroryDTO cateroryDTO = new CateroryDTO(name, imgPaths, catedele.getStatus());
        return ResponseEntity.ok(categoryService.update(id, cateroryDTO));

    }

    @PostMapping("/status/{id}")
    public ResponseEntity<Category> status(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.updateStatus(id));
    }

    @GetMapping("/trash")
    public ResponseEntity<List<CategoryGetAllDTO>>  trash() {
    
            return ResponseEntity.ok(categoryService.getTrash());
    }

    @PostMapping("/update-trash/{id}")
    public ResponseEntity<Category>  updateTrash(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.updateTrash(id));
    }






}
