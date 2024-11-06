package com.phamcongvinh.testusser.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.phamcongvinh.testusser.enity.Category;
import com.phamcongvinh.testusser.enity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByStatusIn(List<Integer> statusList);

    List<Product> findByCategoryAndStatusIn(Category category, List<Integer> status);

    @EntityGraph(attributePaths = { "productImages", "productRock", "productStore", "cartdetail", "productSale" })
    List<Product> findAll();
    List<Product> findByNameContainingIgnoreCase(String name);

}
// Các hàm bắt đầu bằng findBy, readBy, getBy
// findBy: Tìm kiếm dữ liệu theo điều kiện.
// readBy: Giống như findBy, nhưng ngữ nghĩa là "đọc" dữ liệu.
// getBy: Giống như findBy, nhưng ngữ nghĩa là "lấy" dữ liệu.

// Các toán tử so sánh

// IsNull, IsNotNull: Kiểm tra giá trị NULL.
// List<Product> findByDescriptionIsNull();

// GreaterThan, LessThan, Between: So sánh lớn hơn, nhỏ hơn, hoặc nằm trong
// // khoảng.
// List<Product> findByPriceGreaterThan(double price);
// List<Product> findByPriceBetween(double minPrice, double maxPrice);

// Like: Tìm kiếm theo chuỗi có chứa giá trị tương tự (thường dùng cho các chuỗi
// có ký tự đại diện).
// List<Product> findByProductNameLike(String pattern);

// In: Kiểm tra giá trị nằm trong một danh sách.
// List<Product> findByStatusIn(List<Integer> statusList);
