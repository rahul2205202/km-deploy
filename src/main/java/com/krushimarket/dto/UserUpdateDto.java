package com.krushimarket.dto;

public class UserUpdateDto {
    private String name;
    private String phone;
    private String farmName;
    private String location;
    private String imagePath;
    
    public UserUpdateDto() {
    	
    }

	public UserUpdateDto(String name, String phone, String farmName, String location, String imagePath) {
		super();
		this.name = name;
		this.phone = phone;
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
}
