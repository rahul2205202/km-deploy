package com.krushimarket.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "UserReviews")
public class UserReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewID;

    @Column(nullable = false)
    private String consumerName;

    @ManyToOne
    @JoinColumn(name = "userID")
    private User user;

    private Integer rating;

    @Column(columnDefinition = "TEXT")
    private String comment;

    private LocalDateTime reviewDate;
    
    public UserReview() {
    	
    }

	public UserReview(Long reviewID, String consumerName, User user, Integer rating, String comment,
			LocalDateTime reviewDate) {
		super();
		this.reviewID = reviewID;
		this.consumerName = consumerName;
		this.user = user;
		this.rating = rating;
		this.comment = comment;
		this.reviewDate = reviewDate;
	}

	public Long getReviewID() {
		return reviewID;
	}

	public void setReviewID(Long reviewID) {
		this.reviewID = reviewID;
	}

	public String getConsumerName() {
		return consumerName;
	}

	public void setConsumerName(String consumerName) {
		this.consumerName = consumerName;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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

	public LocalDateTime getReviewDate() {
		return reviewDate;
	}

	public void setReviewDate(LocalDateTime reviewDate) {
		this.reviewDate = reviewDate;
	}

	@Override
	public String toString() {
		return "UserReview [reviewID=" + reviewID + ", consumerName=" + consumerName + ", rating="
				+ rating + ", comment=" + comment + ", reviewDate=" + reviewDate + "]";
	}
    
    
}