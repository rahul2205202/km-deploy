package com.krushimarket.dto;

public class ProductCategoryDto {
    private Long categoryID;
    private String categoryName;
    private String description;
    
    public ProductCategoryDto() {
    	
	}

	public ProductCategoryDto(Long categoryID, String categoryName, String description) {
		super();
		this.categoryID = categoryID;
		this.categoryName = categoryName;
		this.description = description;
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
}
