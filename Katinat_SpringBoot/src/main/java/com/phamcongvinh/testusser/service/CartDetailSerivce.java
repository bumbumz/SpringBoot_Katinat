package com.phamcongvinh.testusser.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phamcongvinh.testusser.dto.CartDetailDTO;
import com.phamcongvinh.testusser.dto.CartDetailGetDTO;
import com.phamcongvinh.testusser.enity.CartDetail;
import com.phamcongvinh.testusser.enity.Product;
import com.phamcongvinh.testusser.enity.User;
import com.phamcongvinh.testusser.repository.CartDetailRepository;
import com.phamcongvinh.testusser.repository.ProductRepository;
import com.phamcongvinh.testusser.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CartDetailSerivce {
    @Autowired
    private final CartDetailRepository cartDetailRepository;

    @Autowired
    private final ProductRepository productRepository;

    @Autowired
    private final UserRepository userRepository;

    public List<CartDetailGetDTO> index() {

        List<CartDetail> cartDetail = cartDetailRepository.findAll();
        return cartDetail.stream().map(cart -> {
            Long id = cart.getId();
            Product product = cart.getProduct();
            Long product_id = product.getId();
            Double pricebuy = product.getPricebuy();
            String product_name = product.getName();
            List<String> thumbnails = product.getProductImages().stream()
                    .map(img -> {
                        String thumbnail = img.getThumbnail();
                        return (thumbnail != null && !thumbnail.isEmpty())
                                ? thumbnail
                                : "";
                    })
                    .collect(Collectors.toList());

            User user = cart.getUser();
            Long user_id = user.getUserId();
            String user_name = user.getUsername();
            Long quantity = cart.getQuantity();
            String sugar = cart.getSugar();
            String rock = cart.getRock();
            String note = cart.getNote();

            return new CartDetailGetDTO(id, product_id, product_name, pricebuy, thumbnails, user_id, user_name,
                    quantity, sugar,
                    rock, note);

        }).collect(Collectors.toList());

    }

    // public CartDetail store(CartDetailDTO cartDetailDTO) {
    //     Product product = productRepository.findById(cartDetailDTO.getProductid())
    //             .orElseThrow(() -> new RuntimeException("Product not found"));

    //     User user = userRepository.findById(cartDetailDTO.getUserid())
    //             .orElseThrow(() -> new RuntimeException("User not found"));

    //     CartDetail cart = new CartDetail();
    //     cart.setProduct(product);
    //     cart.setUser(user);
    //     cart.setQuantity(cartDetailDTO.getQuantity());
    //     cart.setSugar(cartDetailDTO.getSugar());
    //     cart.setRock(cartDetailDTO.getRock());
    //     cart.setNote(cartDetailDTO.getNote());
    //     cartDetailRepository.save(cart);
    //     return cart;

    // }

    public CartDetail store(CartDetailDTO cartDetailDTO) {
        // Tìm sản phẩm
        Product product = productRepository.findById(cartDetailDTO.getProductid())
                .orElseThrow(() -> new RuntimeException("Product not found"));
    
        // Tìm người dùng
        User user = userRepository.findById(cartDetailDTO.getUserid())
                .orElseThrow(() -> new RuntimeException("User not found"));
    
        // Kiểm tra xem đã có CartDetail với các thông số đã cho hay chưa
        CartDetail existingCartDetail = cartDetailRepository.findByProductAndUserAndSugarAndRock(
                product, user, cartDetailDTO.getSugar(), cartDetailDTO.getRock());
    
        if (existingCartDetail != null) {
            // Nếu đã tồn tại, tăng số lượng lên 1
            existingCartDetail.setQuantity(existingCartDetail.getQuantity() + 1);
            cartDetailRepository.save(existingCartDetail);
            return existingCartDetail;
        } else {
            // Nếu chưa tồn tại, tạo mới
            CartDetail cart = new CartDetail();
            cart.setProduct(product);
            cart.setUser(user);
            cart.setQuantity(cartDetailDTO.getQuantity());
            cart.setSugar(cartDetailDTO.getSugar());
            cart.setRock(cartDetailDTO.getRock());
            cart.setNote(cartDetailDTO.getNote());
            cartDetailRepository.save(cart);
            return cart;
        }
    }
    

    public CartDetail show(Long id) {
        return cartDetailRepository.findById(id).orElseThrow(() -> new RuntimeException("CartDetail not found"));
    }

    public List<CartDetailGetDTO> geiId(Long id) {
        List<CartDetail> carts = cartDetailRepository.findByUser_UserId(id);
        return carts.stream().map(cart -> {
            Product product = cart.getProduct();
            Long product_id = product.getId();
            String product_name = product.getName();
            List<String> thumbnails = product.getProductImages().stream()
                    .map(img -> {
                        String thumbnail = img.getThumbnail();
                        return (thumbnail != null && !thumbnail.isEmpty())
                                ? thumbnail
                                : "";
                    })
                    .collect(Collectors.toList());
            Long cartid = cart.getId();
            User user = cart.getUser();
            Double pricebuy = product.getPricebuy();
            Long user_id = user.getUserId();
            String user_name = user.getUsername();
            Long quantity = cart.getQuantity();
            String sugar = cart.getSugar();
            String rock = cart.getRock();
            String note = cart.getNote();
            return new CartDetailGetDTO(cartid, product_id, product_name, pricebuy, thumbnails, user_id, user_name,
                    quantity,
                    sugar,
                    rock,
                    note);

        }).collect(Collectors.toList());
    }

    public void CongQuantity(Long id) {
        CartDetail cartdetail = show(id);
        cartdetail.setQuantity(cartdetail.getQuantity() + 1);
        cartDetailRepository.save(cartdetail);
    }

    public void TruQuantity(Long id) {
        CartDetail cartdetail = show(id);
        cartdetail.setQuantity(cartdetail.getQuantity() - 1);
        cartDetailRepository.save(cartdetail);
    }

    public void destroy(Long id) {
        cartDetailRepository.deleteById(id);
    }
    public void destroyAllByUserId(Long userId )
    {
        List<CartDetail>  cartDetails = cartDetailRepository.findByUser_UserId(userId);
        
        if(cartDetails.isEmpty())
        {
            throw new  RuntimeException("Cart is empty");

        }
        cartDetailRepository.deleteAll(cartDetails);

    }

}
