package com.krushimarket.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class UserCreateDto {
	@NotBlank(message = "Please Enter Name")
    private String name;
	@Email(message = "Please Enter Valid Email!")
    private String email;
    private String phone;
    private String password;
    private String farmName;
    private String location;
    private String imagePath;
    
    public UserCreateDto() {
    	
    }

	public UserCreateDto(String name, String email, String phone, String password, String farmName, String location,
			String imagePath) {
		super();
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.password = password;
		this.farmName = farmName;
		this.location = location;
		this.imagePath = imagePath;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
}