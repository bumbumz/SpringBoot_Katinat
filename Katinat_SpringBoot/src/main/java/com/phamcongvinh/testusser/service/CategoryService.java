package com.phamcongvinh.testusser.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phamcongvinh.testusser.dto.CategoryGetAllDTO;
import com.phamcongvinh.testusser.dto.CateroryDTO;
import com.phamcongvinh.testusser.dto.ProductGetAllDTO;
import com.phamcongvinh.testusser.enity.Category;
import com.phamcongvinh.testusser.enity.Sugar;
import com.phamcongvinh.testusser.repository.CategoryRepository;
import com.phamcongvinh.testusser.repository.ProductRepository;
// import com.phamcongvinh.testusser.repository.RockRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CategoryService {
        @Autowired
        private final CategoryRepository categoryRepository;

        @Autowired
        private final ProductRepository productRepository;

        // @Autowired
        // private final RockRepository rockRepository;

        public List<CategoryGetAllDTO> index() {
                List<Category> categories = categoryRepository.findByStatusIn(Arrays.asList(1, 2));
                return categories.stream().map(category -> {
                        List<ProductGetAllDTO> products = productRepository
                                        .findByCategoryAndStatusIn(category, Arrays.asList(1, 2))
                                        .stream()
                                        .map(product -> {
                                                // List<String> rocks = product.getProductRock().stream()
                                                // .map(rock -> {
                                                // Rock rock1 = rockRepository
                                                // .findById(rock.getId())
                                                // .orElseThrow(
                                                // () -> new EntityNotFoundException(
                                                // "Not found"));
                                                // String rockName = rock1.getName();
                                                // return rockName;

                                                // }).collect(Collectors.toList());
                                                List<String> thumbnails = product.getProductImages().stream()
                                                                .map(img -> {
                                                                        String thumbnail = img.getThumbnail();
                                                                        return (thumbnail != null
                                                                                        && !thumbnail.isEmpty())
                                                                                                        ? thumbnail
                                                                                                        : "";
                                                                }).collect(Collectors.toList());

                                                Integer quantity = product.getProductStore().isEmpty() ? null
                                                                : product.getProductStore().get(0).getQuantity();
                                                Double priceSale = product.getProductSale() != null
                                                                ? product.getProductSale().getPriceSale()
                                                                : null;
                                                Double priceBuy = product.getPricebuy();
                                                String description = product.getDescription();
                                                List<String> sugars = product.getProductSugar().stream()
                                                                .map(sugar -> {
                                                                        Sugar sugarr = sugar.getSugar();
                                                                        String sugarr_name = sugarr.getName();
                                                                        return sugarr_name;

                                                                }).collect(Collectors.toList());
                                                List<String> rocks = product.getRocks().stream()
                                                                .map(rock -> {
                                                                        String tyle = rock.getType();
                                                                        return tyle;
                                                                }).collect(Collectors.toList());

                                                return new ProductGetAllDTO(
                                                                product.getId(),
                                                                product.getName(),
                                                                thumbnails,
                                                                quantity,
                                                                priceSale,
                                                                priceBuy,
                                                                description,
                                                                rocks,
                                                                sugars);
                                        }).collect(Collectors.toList());

                        return new CategoryGetAllDTO(
                                        String.valueOf(category.getId()),
                                        category.getName(),
                                        category.getStatus(),
                                        category.getImg(),
                                        products);
                }).collect(Collectors.toList());
        }

        public Category store(CateroryDTO categoryDTO) {
                Category category = new Category();
                category.setName(categoryDTO.getName());
                category.setImg(categoryDTO.getImg());
                category.setStatus(categoryDTO.getStatus());
                categoryRepository.save(category);
                return category;
        }

        public Category show(Long id) {
                return categoryRepository.findById(id).orElse(null);
        }

        public Category update(Long id, CateroryDTO cateroryDTO) {
                Category category = show(id);
                category.setName(cateroryDTO.getName());
                category.setImg(cateroryDTO.getImg());
                categoryRepository.save(category);
                return category;
        }

        public Category updateStatus(Long id) {
                Category category = show(id);
                category.setStatus(category.getStatus() != 1 ? 1 : 2);
                categoryRepository.save(category);
                return category;
        }

        public List<CategoryGetAllDTO> getTrash() {
                List<Category> categories = categoryRepository.findByStatus(0);
                return categories.stream().map(category -> {
                        List<ProductGetAllDTO> products = productRepository.findByStatusIn(Arrays.asList(1, 2))
                                        .stream()
                                        .map(product -> {
                                                // List<String> rocks = product.getProductRock().stream()
                                                // .map(rock -> {
                                                // Rock rock1 = rockRepository
                                                // .findById(rock.getId())
                                                // .orElseThrow(
                                                // () -> new EntityNotFoundException(
                                                // "Not found"));
                                                // String rockName = rock1.getName();
                                                // return rockName;

                                                // }).collect(Collectors.toList());
                                                List<String> thumbnails = product.getProductImages().stream()
                                                                .map(img -> {
                                                                        String thumbnail = img.getThumbnail();
                                                                        return (thumbnail != null && !thumbnail
                                                                                        .isEmpty()) ?  thumbnail
                                                                                                        : "";
                                                                }).collect(Collectors.toList());

                                                Integer quantity = product.getProductStore().isEmpty() ? null
                                                                : product.getProductStore().get(0).getQuantity();
                                                Double priceSale = product.getProductSale() != null
                                                                ? product.getProductSale().getPriceSale()
                                                                : null;
                                                Double priceBuy = product.getPricebuy();
                                                String description = product.getDescription();
                                                List<String> sugars = product.getProductSugar().stream()
                                                                .map(sugar -> {
                                                                        Sugar sugarr = sugar.getSugar();
                                                                        String sugarr_name = sugarr.getName();
                                                                        return sugarr_name;

                                                                }).collect(Collectors.toList());
                                                List<String> rocks = product.getRocks().stream()
                                                                .map(rock -> {
                                                                        String tyle = rock.getType();
                                                                        return tyle;
                                                                }).collect(Collectors.toList());
                                                return new ProductGetAllDTO(
                                                                product.getId(),
                                                                product.getName(),
                                                                thumbnails,
                                                                quantity,
                                                                priceSale,
                                                                priceBuy,
                                                                description,
                                                                rocks,
                                                                sugars);
                                        }).collect(Collectors.toList());

                        return new CategoryGetAllDTO(
                                        String.valueOf(category.getId()),
                                        category.getName(),
                                        category.getStatus(),
                                        category.getImg(),
                                        products);
                }).collect(Collectors.toList());
        }

        public Category updateTrash(Long id) {
                Category category = show(id);
                category.setStatus(0);
                categoryRepository.save(category);
                return category;
        }
}
