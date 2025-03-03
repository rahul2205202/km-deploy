package com.krushimarket.dto;

import java.time.LocalDateTime;

import com.krushimarket.model.Availability;

public class ProductDto {
    private Long productID;
    private Long userID;
    private String productName;
    private String description;
    private Long categoryID;
    private String phoneNumber;
    private String basePrice;
    private String quantity;
    private String imagePath;
    private com.krushimarket.model.Availability availability;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    public ProductDto() {
    	
    }

	public ProductDto(Long productID, Long userID, String productName, String description, Long categoryID,
			String phoneNumber, String basePrice, String quantity, String imagePath, Availability availability,
			LocalDateTime createdAt, LocalDateTime updatedAt) {
		super();
		this.productID = productID;
		this.userID = userID;
		this.productName = productName;
		this.description = description;
		this.categoryID = categoryID;
		this.phoneNumber = phoneNumber;
		this.basePrice = basePrice;
		this.quantity = quantity;
		this.imagePath = imagePath;
		this.availability = availability;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public Long getProductID() {
		return productID;
	}

	public void setProductID(Long productID) {
		this.productID = productID;
	}

	public Long getUserID() {
		return userID;
	}

	public void setUserID(Long userID) {
		this.userID = userID;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(Long categoryID) {
		this.categoryID = categoryID;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(String basePrice) {
		this.basePrice = basePrice;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public com.krushimarket.model.Availability getAvailability() {
		return availability;
	}

	public void setAvailability(com.krushimarket.model.Availability availability) {
		this.availability = availability;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	
}
