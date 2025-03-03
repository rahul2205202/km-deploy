package com.krushimarket.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.krushimarket.dto.ProductCreateDto;
import com.krushimarket.dto.ProductDto;
import com.krushimarket.dto.ProductUpdateDto;
import com.krushimarket.exception.CustomException;
import com.krushimarket.service.AWSService;
import com.krushimarket.service.ProductService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
//@CrossOrigin(origins = "http://localhost:3000") 
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;
    
    @Autowired
    private AWSService awsService;

    @GetMapping("/all")
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        List<ProductDto> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }
    
    @PostMapping("/add")
    public ResponseEntity<?> addProduct(@RequestParam("image") MultipartFile image,
                                        @ModelAttribute ProductCreateDto createDto) {
        try {
        	System.out.println(createDto);
            createDto.setImagePath(awsService.uploadImage(image));
            return ResponseEntity.ok(productService.createProduct(createDto));

        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading image: " + e.getMessage());
        } catch (CustomException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    
    @PutMapping("update/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody ProductUpdateDto updateDto) {
    	try {
			return ResponseEntity.ok(productService.updateProduct(id, updateDto));
		} catch (CustomException e) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
    }
    
    @GetMapping("/details/{id}")
    public ResponseEntity<?> getDetails(@PathVariable Long id) {
        try {
			return ResponseEntity.ok(productService.getDetails(id));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id){
    	try {
    		productService.deleteProduct(id);
			return ResponseEntity.status(HttpStatus.OK).build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
    }
    
    @GetMapping("/user/{id}")
    public ResponseEntity<?> getProducts(@PathVariable Long id){
    	try {
			return ResponseEntity.ok(productService.getByUser(id));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
    }
    
    @GetMapping("/{categoryName}")
    public ResponseEntity<?> getProducts(@PathVariable String categoryName){
    	try {
			return ResponseEntity.ok(productService.getByCategory(categoryName));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
    }
}
