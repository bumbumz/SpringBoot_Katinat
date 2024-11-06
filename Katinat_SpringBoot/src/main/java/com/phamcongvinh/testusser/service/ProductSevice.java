package com.phamcongvinh.testusser.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phamcongvinh.testusser.dto.ProductDTO;
import com.phamcongvinh.testusser.dto.ProductGetAllDTO;
import com.phamcongvinh.testusser.enity.Category;
import com.phamcongvinh.testusser.enity.Product;
import com.phamcongvinh.testusser.enity.ProductImage;
import com.phamcongvinh.testusser.enity.ProductSale;
import com.phamcongvinh.testusser.enity.ProductStore;
import com.phamcongvinh.testusser.enity.Sugar;
import com.phamcongvinh.testusser.repository.CategoryRepository;
import com.phamcongvinh.testusser.repository.ProductImageRepository;
import com.phamcongvinh.testusser.repository.ProductRepository;
import com.phamcongvinh.testusser.repository.ProductSaleRepository;
import com.phamcongvinh.testusser.repository.ProductStoreRepository;
// import com.phamcongvinh.testusser.repository.RockRepository;

import jakarta.persistence.EntityNotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductSevice {
    @Autowired
    private final ProductRepository productRepository;
    @Autowired
    private final ProductImageRepository productImageRepository;

    @Autowired
    private final ProductStoreRepository productStoreRepository;

    @Autowired
    private final ProductSaleRepository productSaleRepository;

    @Autowired
    private final CategoryRepository categoryRepository;
    // @Autowired
    // private final RockRepository rockRepository;

    public List<ProductGetAllDTO> index() {
        List<Product> products = productRepository.findByStatusIn(Arrays.asList(1, 2));

        return products.stream().map(product -> {
            // List<String> rocks=product.().stream()
            // .map(rock->{
            // Rock rock1=rockRepository.findById(rock.getId()).orElseThrow(
            // ()->new EntityNotFoundException("Not found")
            // );
            // String rockName=rock1.getName();
            // return rockName;

            // }).collect(Collectors.toList());
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

            List<String> thumbnails = product.getProductImages().stream()
                    .map(img -> {
                        String thumbnail = img.getThumbnail();
                        return (thumbnail != null && !thumbnail.isEmpty())
                                ? thumbnail
                                : "";
                    })
                    .collect(Collectors.toList());
            Integer quantity = product.getProductStore().isEmpty() ? null
                    : product.getProductStore().get(0).getQuantity();
            Double priceSale = product.getProductSale() != null ? product.getProductSale().getPriceSale() : null;
            Double priceBuy = product.getPricebuy();
            String description = product.getDescription();
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
    }

    public Product store(ProductDTO productDTO) {
        // Optional là một container (đối tượng chứa) có thể chứa một giá trị không null
        // hoặc có thể trống (null).
        Optional<Category> category = categoryRepository.findById(productDTO.getCategoryId());
        if (category.isPresent()) {
            Product product = new Product();
            Category categorys = category.get();
            product.setName(productDTO.getName());
            product.setSlug(productDTO.getSlug());
            product.setContent(productDTO.getContent());
            product.setDescription(productDTO.getDescription());
            product.setPricebuy(productDTO.getPricebuy());
            product.setStatus(productDTO.getStatus());
            product.setCategory(categorys);
            product.setCreatedAt(LocalDateTime.now());
            // LocalDateTime.now() thời điểm hiện tại
            product = productRepository.save(product);
            if (productDTO.getThumbnail() != null && !productDTO.getThumbnail().isEmpty()) {
                for (String thumbnail : productDTO.getThumbnail()) {
                    ProductImage productImage = new ProductImage();
                    productImage.setProduct(product);
                    productImage.setThumbnail(thumbnail);

                    productImageRepository.save(productImage);
                }
            }
            ProductStore productStore = new ProductStore();
            productStore.setProduct(product);
            productStore.setQuantity(productDTO.getQuantity());
            productStore.setCreatedAt(LocalDate.now());
            productStoreRepository.save(productStore);

            ProductSale productSale = new ProductSale();
            productSale.setProduct(product);
            productSale.setPriceSale(0.0);
            productSale.setCreateAt(LocalDate.now());
            productSale.setStatus(2);
            productSaleRepository.save(productSale);

            return product;

        }

        throw new EntityNotFoundException("Categoty không tồn tại");

    }

    public Product show(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public String showimage(Long id) {
        Product product = show(id);
        if (product != null) {
            List<String> thumbnails = product.getProductImages().stream()
                    .map(img -> img.getThumbnail() != null && !img.getThumbnail().isEmpty() ? img.getThumbnail() : "")
                    .collect(Collectors.toList());

            // Return the first thumbnail if available
            return thumbnails.isEmpty() ? "http://localhost:8080/api/img/" + thumbnails.get(0)
                    : "http://localhost:8080/api/img/" + thumbnails.get(0);
        }
        return ""; // Return an empty string if the product is null
    }

    public Product updateStatus(Long id) {
        Product product = show(id);
        product.setStatus(product.getStatus() != 1 ? 1 : 2);
        productRepository.save(product);
        return product;
    }

    public Product updateTrash(Long id) {
        Product product = show(id);
        product.setStatus(0);
        productRepository.save(product);
        return product;

    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<ProductGetAllDTO> search(String query) {
        List<Product> products = productRepository.findByNameContainingIgnoreCase(query);

        return products.stream().map(product -> {
            List<String> sugars = product.getProductSugar().stream()
                    .map(sugar -> sugar.getSugar().getName())
                    .collect(Collectors.toList());

            List<String> rocks = product.getRocks().stream()
                    .map(rock -> rock.getType())
                    .collect(Collectors.toList());

            List<String> thumbnails = product.getProductImages().stream()
                    .map(img -> img.getThumbnail() != null && !img.getThumbnail().isEmpty() ? img.getThumbnail() : "")
                    .collect(Collectors.toList());

            Integer quantity = product.getProductStore().isEmpty() ? null
                    : product.getProductStore().get(0).getQuantity();
            Double priceSale = product.getProductSale() != null ? product.getProductSale().getPriceSale() : null;
            Double priceBuy = product.getPricebuy();
            String description = product.getDescription();

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
    }

}
