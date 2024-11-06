package com.phamcongvinh.testusser.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.phamcongvinh.testusser.dto.CateroryDTO;
import com.phamcongvinh.testusser.dto.NewsEvenDTO;
import com.phamcongvinh.testusser.enity.Category;
import com.phamcongvinh.testusser.enity.NewsEven;
import com.phamcongvinh.testusser.service.NewsEvenService;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping("api/news-event")
@AllArgsConstructor
public class NewsEvenController {
    @Autowired
    private final NewsEvenService newsEvenService;

    @Autowired
    private final FileController fileController;

    @GetMapping
    public ResponseEntity<List<NewsEven>> index() {
        return ResponseEntity.ok(newsEvenService.index());
    }

    @PostMapping("/store")
    public ResponseEntity<NewsEven> store(
            @RequestParam("name") String name,
            @RequestParam(value = "thumbnail", required = false) MultipartFile thumbnail,
            @RequestParam(value = "image", required = false) MultipartFile image,
            @RequestParam("content") String content

    ) {
        String thumbnailPaths = fileController.uploadFile(thumbnail);
        String imagePaths = fileController.uploadFile(image);
        NewsEvenDTO newsEvenDTO = new NewsEvenDTO(name, thumbnailPaths, imagePaths, content);
        return ResponseEntity.ok(newsEvenService.store(newsEvenDTO));

    }
}
