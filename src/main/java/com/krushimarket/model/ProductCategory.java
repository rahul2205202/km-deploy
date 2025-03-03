package com.krushimarket.model;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "ProductCategories")
public class ProductCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryID;

    @Column(nullable = false)
    private String categoryName;

    @Column(columnDefinition = "TEXT")
    private String description;
    
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Product> products;
    
    public ProductCategory() {
    	
    }

	public ProductCategory(Long categoryID, String categoryName, String description, List<Product> products) {
		super();
		this.categoryID = categoryID;
		this.categoryName = categoryName;
		this.description = description;
		this.products = products;
	}

	public Long getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(Long categoryID) {
		this.categoryID = categoryID;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
}

