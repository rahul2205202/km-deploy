package com.krushimarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.krushimarket.model.Product;
import com.krushimarket.model.ProductCategory;
import com.krushimarket.model.User;

import java.util.List;


public interface ProductRepository extends JpaRepository<Product, Long> {
	List<Product> findByUser(User user);
	List<Product> findByCategory(ProductCategory category);
}
