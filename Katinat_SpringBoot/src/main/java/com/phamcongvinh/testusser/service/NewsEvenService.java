package com.phamcongvinh.testusser.service;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phamcongvinh.testusser.dto.NewsEvenDTO;
import com.phamcongvinh.testusser.enity.NewsEven;
import com.phamcongvinh.testusser.repository.NewsEvenRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class NewsEvenService {

    @Autowired
    private final NewsEvenRepository newsEvenRepository;

    public List<NewsEven> index()
    {
        return newsEvenRepository.findAll();
    }

    public NewsEven store(NewsEvenDTO newsEvenDTO)

    {
        NewsEven newsEven = new NewsEven();
        newsEven.setName(newsEvenDTO.getName());
        newsEven.setThumbnail(newsEvenDTO.getThumbnail());
        newsEven.setImage(newsEvenDTO.getImage());
        newsEven.setContent(newsEvenDTO.getContent());
        newsEvenRepository.save(newsEven);
        return newsEven;

    }

}
