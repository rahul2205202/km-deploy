package com.krushimarket.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.*;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Subscriptions")
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long subscriptionID;

    @OneToOne
    @JoinColumn(name = "userID")
    private User user;

    @Enumerated(EnumType.STRING)
    private SubscriptionType subscriptionType;

    @Column(nullable = false)
    private LocalDate startDate;

    private LocalDate endDate;

    @Column(precision = 10, scale = 2)
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    private SubscriptionStatus status;
    
    public Subscription() {
    	
    }

	public Subscription(Long subscriptionID, User user, SubscriptionType subscriptionType, LocalDate startDate,
			LocalDate endDate, BigDecimal price, SubscriptionStatus status) {
		super();
		this.subscriptionID = subscriptionID;
		this.user = user;
		this.subscriptionType = subscriptionType;
		this.startDate = startDate;
		this.endDate = endDate;
		this.price = price;
		this.status = status;
	}

	public Long getSubscriptionID() {
		return subscriptionID;
	}

	public void setSubscriptionID(Long subscriptionID) {
		this.subscriptionID = subscriptionID;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public SubscriptionType getSubscriptionType() {
		return subscriptionType;
	}

	public void setSubscriptionType(SubscriptionType subscriptionType) {
		this.subscriptionType = subscriptionType;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public SubscriptionStatus getStatus() {
		return status;
	}

	public void setStatus(SubscriptionStatus status) {
		this.status = status;
	}   
}
