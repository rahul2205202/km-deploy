package com.krushimarket.model;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userID;

    @Column(nullable = true)
    private String name;

    @Column(nullable = true, unique = true)
    private String email;

    @Column(nullable = true, unique = true)
    private String phone;

    @Column(nullable = true)
    private String password;

    private String farmName;
    private String location;

    @Column(length = 500)
    private String imagePath;
    
    @CreationTimestamp
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Product> products;

    @OneToOne(mappedBy = "user" , cascade = CascadeType.ALL)
    private Subscription subscription;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserReview> userReviews;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Notification> notifications;
    
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList(); // Return an empty list
    }
    
    public User() {
    	
    }

	public User(Long userID, String name, String email, String phone, String password, String farmName, String location,
			String imagePath, LocalDateTime createdAt, List<Product> products, Subscription subscription,
			List<UserReview> userReviews, List<Notification> notifications) {
		super();
		this.userID = userID;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.password = password;
		this.farmName = farmName;
		this.location = location;
		this.imagePath = imagePath;
		this.createdAt = createdAt;
		this.products = products;
		this.subscription = subscription;
		this.userReviews = userReviews;
		this.notifications = notifications;
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

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Subscription getSubscription() {
		return subscription;
	}

	public void setSubscription(Subscription subscription) {
		this.subscription = subscription;
	}

	public List<UserReview> getUserReviews() {
		return userReviews;
	}

	public void setUserReviews(List<UserReview> userReviews) {
		this.userReviews = userReviews;
	}

	public List<Notification> getNotifications() {
		return notifications;
	}

	public void setNotifications(List<Notification> notifications) {
		this.notifications = notifications;
	}

	@Override
	public String toString() {
		return "User [userID=" + userID + ", name=" + name + ", email=" + email + ", phone=" + phone + ", password="
				+ password + ", farmName=" + farmName + ", location=" + location + ", imagePath=" + imagePath
				+ ", createdAt=" + createdAt + ", products=" + products + ", subscription=" + subscription
				+ ", userReviews=" + userReviews + ", notifications=" + notifications + "]";
	}
	
}