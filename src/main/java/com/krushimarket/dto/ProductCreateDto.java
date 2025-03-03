package com.krushimarket.dto;

import java.math.BigDecimal;

import com.krushimarket.model.Availability;


public class ProductCreateDto {
    private Long userID;
    private String productName;
    private String description;
    private Long categoryID;
    private String basePrice;
    private String quantity;
    private String imagePath;
    private com.krushimarket.model.Availability availability;
    
    public ProductCreateDto() {
    	
    }

	public ProductCreateDto(Long userID, String productName, String description, Long categoryID, String basePrice,
			String quantity, String imagePath, Availability availability) {
		super();
		this.userID = userID;
		this.productName = productName;
		this.description = description;
		this.categoryID = categoryID;
		this.basePrice = basePrice;
		this.quantity = quantity;
		this.imagePath = imagePath;
		this.availability = availability;
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
}
