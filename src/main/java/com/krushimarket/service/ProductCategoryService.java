package com.krushimarket.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.krushimarket.dto.ProductCategoryDto;
import com.krushimarket.model.ProductCategory;
import com.krushimarket.repository.CategoryRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProductCategoryService {
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ModelMapper mapper;
	
	public List<ProductCategoryDto> getAll() {
		List<ProductCategory> categories = categoryRepository.findAll();
		return categories.stream()
                .map(user -> mapper.map(user, ProductCategoryDto.class))
                .collect(Collectors.toList());
	}
}
