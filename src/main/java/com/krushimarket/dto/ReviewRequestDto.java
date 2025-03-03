package com.krushimarket.dto;

import jakarta.validation.constraints.*;

public class ReviewRequestDto {
    private String consumerName;
    private Integer rating;
    private String comment;
    
    public ReviewRequestDto() {
    	
    }
    
    public ReviewRequestDto(String consumerName, Integer rating, String comment) {
		super();
		this.consumerName = consumerName;
		this.rating = rating;
		this.comment = comment;
	}

	public String getConsumerName() {
        return consumerName;
    }

    public void setConsumerName(String consumerName) {
        this.consumerName = consumerName;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}