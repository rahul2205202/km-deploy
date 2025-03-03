package com.krushimarket.dto;
import java.math.BigDecimal;
import java.time.LocalDate;

import com.krushimarket.model.SubscriptionType;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public class SubscriptionRequestDto {
	
    private Long userId;
    private SubscriptionType subscriptionType;
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal price;
    
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public SubscriptionType getSubscriptionType() {
        return subscriptionType;
    }
    

    public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}