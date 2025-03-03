package com.krushimarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.krushimarket.service.ProductCategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {
	@Autowired
	private ProductCategoryService categoryService;
	
	@GetMapping("/all")
	public ResponseEntity<?> getAll(){
		try {
			return ResponseEntity.ok(categoryService.getAll());
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
}
