package com.krushimarket.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.krushimarket.model.ProductCategory;

public interface CategoryRepository extends JpaRepository<ProductCategory, Long> {
	
	List<ProductCategory> findByCategoryName(String categoryName);
}
