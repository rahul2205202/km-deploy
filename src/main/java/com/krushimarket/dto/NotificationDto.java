package com.krushimarket.dto;

import jakarta.validation.constraints.NotBlank;

public class NotificationDto {
	@NotBlank
    private String message;
    private Boolean isRead;
    private Long userId;
    
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getIsRead() {
        return isRead;
    }

    public void setIsRead(Boolean isRead) {
        this.isRead = isRead;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}