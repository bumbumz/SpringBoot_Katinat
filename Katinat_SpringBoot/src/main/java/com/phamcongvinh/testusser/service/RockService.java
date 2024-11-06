package com.phamcongvinh.testusser.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phamcongvinh.testusser.enity.Product;
import com.phamcongvinh.testusser.enity.Rock;
import com.phamcongvinh.testusser.repository.ProductRepository;
import com.phamcongvinh.testusser.repository.RockRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RockService {
    @Autowired
    private final RockRepository rockRepository;

    @Autowired
    private final ProductRepository productRepository;

    public Product store(Long id, String name) {
        Optional<Product> productOptional = productRepository.findById(id);

        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            Rock rock = new Rock();
            rock.setType(name);
            rock.setProduct(product);
            rockRepository.save(rock);

            return product;
        } else {
            throw new RuntimeException("Product không tồn tại");
        }

    }

    public List<Rock> getid(Long id) {
        List<Rock> rocks = rockRepository.findByProductId(id);

        if (rocks.isEmpty()) {
            throw new RuntimeException("Không tìm thấy Rock nào cho sản phẩm với ID: " + id);
        }

        return rocks; 

    }

}
