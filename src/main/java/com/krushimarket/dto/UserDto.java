package com.krushimarket.dto;

import lombok.Data;
import java.time.LocalDateTime;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class UserDto {
    private Long userID;
    @NotBlank(message = "Please Enter Name")
    private String name;
    @Email(message = "Please Enter Email")
    private String email;
    private String phone;
    private String farmName;
    private String location;
    private String imagePath;
    private LocalDateTime createdAt;
    
    private UserDto() {
    	
    }

	public UserDto(Long userID, String name, String email, String phone, String farmName, String location,
			String imagePath, LocalDateTime createdAt) {
		super();
		this.userID = userID;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.farmName = farmName;
		this.location = location;
		this.imagePath = imagePath;
		this.createdAt = createdAt;
	}

	public Long getUserID() {
		return userID;
	}

	public void setUserID(Long userID) {
		this.userID = userID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFarmName() {
		return farmName;
	}

	public void setFarmName(String farmName) {
		this.farmName = farmName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
}