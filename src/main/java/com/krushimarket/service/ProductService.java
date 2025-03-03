package com.krushimarket.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.krushimarket.dto.ProductCreateDto;
import com.krushimarket.dto.ProductDto;
import com.krushimarket.dto.ProductUpdateDto;
import com.krushimarket.exception.CustomException;
import com.krushimarket.model.Product;
import com.krushimarket.model.ProductCategory;
import com.krushimarket.model.User;
import com.krushimarket.repository.CategoryRepository;
import com.krushimarket.repository.ProductRepository;
import com.krushimarket.repository.UserRepository;

@Service
public class ProductService {
	@Autowired
	private  ProductRepository repository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ModelMapper mapper;
	
	public List<ProductDto> getAllProducts() {
        List<Product> products = repository.findAll();
        return products.stream()
                .map(product -> {
                    ProductDto productDto = mapper.map(product, ProductDto.class);
                    User user = product.getUser();
                    if (user != null) {
                        productDto.setPhoneNumber(user.getPhone());
                    } else {
                        productDto.setPhoneNumber(null);
                    }
                    return productDto;
                })
                .collect(Collectors.toList());
    }
	
	public ProductCreateDto createProduct(ProductCreateDto createDto) {
		Product product = mapper.map(createDto, Product.class);
		User user = userRepository.findById(createDto.getUserID()).orElseThrow( () -> new CustomException("User not Found"));
		ProductCategory category = categoryRepository.findById(createDto.getCategoryID()).orElseThrow( () -> new CustomException("Category Not Found"));
		product.setCategory(category);
		product.setUser(user);
		Product createdProduct = repository.save(product);
		if(createdProduct==null) {
			throw new CustomException("Failed to Add Product");
		}
		return mapper.map(createdProduct, ProductCreateDto.class);
	}
	
	public ProductUpdateDto updateProduct(Long id, ProductUpdateDto updateDto) {
	    Optional<Product> existingProduct = repository.findById(id);
	    if (existingProduct.isPresent()) {
	        Product product = existingProduct.get();
	        product.setProductName(updateDto.getProductName());
	        product.setBasePrice(updateDto.getBasePrice());
	        product.setQuantity(updateDto.getQuantity());
	        Product updatedProduct = repository.save(product);
	        return mapper.map(updatedProduct, ProductUpdateDto.class);
	    } else {
	        throw new CustomException("Product Not Found");
	    }
	}
	
	public ProductDto getDetails(Long id) {
		Product product = repository.findById(id).orElseThrow( () -> new CustomException("Product Not Found"));
		ProductDto productDto = mapper.map(product, ProductDto.class);
		productDto.setPhoneNumber(product.getUser().getPhone());
		System.out.println(productDto.getPhoneNumber());
		return productDto;
	}
	
	public void deleteProduct(Long id) {
		Product product = repository.findById(id).orElseThrow(()-> new CustomException("Product not Found"));
		repository.delete(product);
	}
	
	public List<ProductDto> getByUser(Long userid) {
		User user = userRepository.findById(userid).orElseThrow( () -> new CustomException("User not Found"));
		List<Product> products = repository.findByUser(user);
		if(products.isEmpty()) {
			throw new CustomException("No Products Found");
		}
		return products.stream()
                .map(product -> mapper.map(product, ProductDto.class))
                .collect(Collectors.toList());
	}
	
	public List<ProductDto> getByCategory(String categoryName) {
		ProductCategory category = categoryRepository.findByCategoryName(categoryName).get(0);
		List<Product> products = repository.findByCategory(category);
		if(products.isEmpty()) {
			throw new CustomException("No Products Found");
		}
		return products.stream()
                .map(product -> mapper.map(product, ProductDto.class))
                .collect(Collectors.toList());
	}
}
