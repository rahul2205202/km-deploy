package com.krushimarket.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Notifications")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long notificationID;

    @ManyToOne
    @JoinColumn(name = "userID")
    private User user;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String message;

    private Boolean isRead;
    
    @CreationTimestamp
    private LocalDateTime createdAt;
    
    public Notification() {
    	
    }

	public Notification(Long notificationID, User user, String message, Boolean isRead, LocalDateTime createdAt) {
		super();
		this.notificationID = notificationID;
		this.user = user;
		this.message = message;
		this.isRead = isRead;
		this.createdAt = createdAt;
	}

	public Long getNotificationID() {
		return notificationID;
	}

	public void setNotificationID(Long notificationID) {
		this.notificationID = notificationID;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

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

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
}