package com.krushimarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.krushimarket.model.UserReview;

public interface UserReviewRepository extends JpaRepository<UserReview, Long>{

}
