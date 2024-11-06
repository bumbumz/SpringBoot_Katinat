package com.phamcongvinh.testusser.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phamcongvinh.testusser.dto.ProductSaleDTO;
import com.phamcongvinh.testusser.dto.ProductSaleGetAllDTO;
import com.phamcongvinh.testusser.enity.Product;
import com.phamcongvinh.testusser.enity.ProductSale;
import com.phamcongvinh.testusser.enity.Sugar;
import com.phamcongvinh.testusser.repository.ProductSaleRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductSaleService {
    @Autowired
    private final ProductSaleRepository productSaleRepository;

    public ProductSale update(Long id, ProductSaleDTO productSaleDTO) {
        ProductSale productSale = productSaleRepository.findByProductId(id);
        productSale.setPriceSale(productSaleDTO.getPriceSale());
        productSale.setStatus(productSale.getStatus() == 1 ? 2 : 1);
        productSaleRepository.save(productSale);
        return productSale;
    }

    public List<ProductSaleGetAllDTO> index() {
        List<ProductSale> products = productSaleRepository.findByStatusIn(Arrays.asList(1));
        return products.stream().map(productsale -> {
            Long id = productsale.getId();
            Double priceSale = productsale.getPriceSale();
            Product product = productsale.getProduct();
            String name = product.getName();
            Double priceBuy = product.getPricebuy();
            String description = product.getDescription();
            List<String> sugars=product.getProductSugar().stream()
            .map(sugar->{
                Sugar sugarr=sugar.getSugar();
                String sugarr_name= sugarr.getName();
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
            return new ProductSaleGetAllDTO(id, priceSale, name, priceBuy, description, rocks,sugars, thumbnails);

        }).collect(Collectors.toList());

    }

}
