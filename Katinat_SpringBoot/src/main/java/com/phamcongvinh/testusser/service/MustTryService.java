package com.phamcongvinh.testusser.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;

import com.phamcongvinh.testusser.dto.MustTryProductDTO;
import com.phamcongvinh.testusser.dto.MustTryProductGetDTO;
import com.phamcongvinh.testusser.enity.MustTryProduct;
import com.phamcongvinh.testusser.enity.Product;
import com.phamcongvinh.testusser.enity.Rock;
import com.phamcongvinh.testusser.enity.Sugar;
import com.phamcongvinh.testusser.repository.MustTryRepository;
import com.phamcongvinh.testusser.repository.ProductRepository;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import java.util.List;

@Service
@AllArgsConstructor
public class MustTryService {
    @Autowired
    private final MustTryRepository mustTryRepository;
    @Autowired
    private final ProductRepository productRepository;

    public MustTryProduct store(MustTryProductDTO mustTryProductDTO) {
        Product product = productRepository.findById(mustTryProductDTO.getProductid())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        MustTryProduct musttryProduct = new MustTryProduct();
        musttryProduct.setCreatedAt(LocalDateTime.now());
        musttryProduct.setProduct(product);

        mustTryRepository.save(musttryProduct);
        return musttryProduct;

    }

    public List<MustTryProductGetDTO> index() {
        List<MustTryProduct> mustTryProduct = mustTryRepository.findAll();
        return mustTryProduct.stream().map(
                product -> {
                    Long id = product.getId();
                    Product product_in_must = product.getProduct();
                    Double price = product_in_must.getPricebuy();

                    Long product_id = product_in_must.getId();
                    String product_name = product_in_must.getName();
                    Double priceBuy = product_in_must.getPricebuy();
                    String description = product_in_must.getDescription();
                    List<String> sugars = product_in_must.getProductSugar().stream()
                            .map(sugar -> {
                                Sugar sugarr = sugar.getSugar();
                                String sugarr_name = sugarr.getName();
                                return sugarr_name;

                            }).collect(Collectors.toList());
                    List<String> rocks = product_in_must.getRocks().stream()
                            .map(rock -> {
                                String tyle = rock.getType();
                                return tyle;
                            }).collect(Collectors.toList());
                    List<String> thumbnails = product_in_must.getProductImages().stream()
                            .map(img -> {
                                String thumbnail = img.getThumbnail();
                                return (thumbnail != null && !thumbnail.isEmpty())
                                        ? thumbnail
                                        : "";
                            })
                            .collect(Collectors.toList());
                    return new MustTryProductGetDTO(
                            id,
                            product_id,
                            product_name,
                            price,
                            priceBuy,
                            description,
                            rocks,
                            sugars,
                            thumbnails);

                }).collect(Collectors.toList());

    }

}
