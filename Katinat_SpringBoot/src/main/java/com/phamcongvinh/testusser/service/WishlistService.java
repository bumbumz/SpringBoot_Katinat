package com.phamcongvinh.testusser.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phamcongvinh.testusser.dto.CartDetailGetDTO;
import com.phamcongvinh.testusser.dto.WishlistDTO;
import com.phamcongvinh.testusser.dto.WishlistGetAllDTO;
import com.phamcongvinh.testusser.dto.WishlistProUseDTO;
import com.phamcongvinh.testusser.enity.CartDetail;
import com.phamcongvinh.testusser.enity.Product;
import com.phamcongvinh.testusser.enity.User;
import com.phamcongvinh.testusser.enity.Wishlist;
import com.phamcongvinh.testusser.repository.ProductRepository;
import com.phamcongvinh.testusser.repository.UserRepository;
import com.phamcongvinh.testusser.repository.WishlistRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class WishlistService {
    @Autowired
    private final WishlistRepository wishlistRepository;

    @Autowired
    private final ProductRepository productRepository;

    @Autowired
    private final UserRepository userRepository;

    public Wishlist store(WishlistDTO wishlistDTO) {
        Product product = productRepository.findById(wishlistDTO.getProductid())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        User user = userRepository.findById(wishlistDTO.getUserid())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Wishlist wishlist = new Wishlist();
        wishlist.setProduct(product);
        wishlist.setUser(user);
        wishlist.setQuantity(wishlistDTO.getQuantity());
        wishlist.setSugar(wishlistDTO.getSugar());
        wishlist.setRock(wishlistDTO.getRock());
        wishlist.setNote(wishlistDTO.getNote());
        wishlistRepository.save(wishlist);
        return wishlist;

    }

    public List<WishlistGetAllDTO> index() {
        List<Wishlist> wishlist = wishlistRepository.findAll();
        return wishlist.stream().map(
                wish -> {
                    Long id = wish.getId();
                    Product product = wish.getProduct();
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
                    User user = wish.getUser();
                    Long user_id = user.getUserId();
                    String user_name = user.getUsername();
                    Long quantity = wish.getQuantity();
                    String sugar = wish.getSugar();
                    String rock = wish.getRock();
                    String note = wish.getNote();
                    return new WishlistGetAllDTO(id, product_id, product_name, pricebuy, thumbnails, user_id, user_name,
                            quantity, sugar,
                            rock, note);
                }).collect(Collectors.toList());
    }

    public Wishlist show(Long id) {
        return wishlistRepository.findById(id).orElseThrow(() -> new RuntimeException("CartDetail not found"));
    }

    public WishlistProUseDTO geiProUse(Long proId, Long userId) {
        boolean exists = wishlistRepository.findByUser_UserIdAndProduct_Id(userId, proId).isPresent();

        return new WishlistProUseDTO(exists);
    }

    public List<WishlistGetAllDTO> geiId(Long id) {
        List<Wishlist> carts = wishlistRepository.findByUser_UserId(id);
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
            return new WishlistGetAllDTO(cartid, product_id, product_name, pricebuy, thumbnails, user_id, user_name,
                    quantity,
                    sugar,
                    rock,
                    note);

        }).collect(Collectors.toList());
    }

    public void destroy(Long id) {
        wishlistRepository.deleteById(id);
    }

    
    public void destroy(Long proId, Long userId) {
        Wishlist exists = wishlistRepository.findByUser_UserIdAndProduct_Id(userId, proId)
                .orElseThrow(() -> new RuntimeException("Wishlist not found"));

        // Xóa đối tượng Wishlist
        wishlistRepository.deleteById(exists.getId());
    }

    public void destroyAllByUserId(Long userId) {
        List<Wishlist> wishlistItems = wishlistRepository.findByUser_UserId(userId);

        if (wishlistItems.isEmpty()) {
            throw new RuntimeException("No wishlist items found for user with ID: " + userId);
        }

        wishlistRepository.deleteAll(wishlistItems);

        // deleteAll sẽ xóa tất cả các bản ghi của một danh sách các thực thể được
        // truyền vào
    }

}
