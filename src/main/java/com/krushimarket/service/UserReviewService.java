package com.krushimarket.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.krushimarket.dto.ReviewRequestDto;
import com.krushimarket.exception.CustomException;
import com.krushimarket.model.User;
import com.krushimarket.model.UserReview;
import com.krushimarket.repository.UserRepository;
import com.krushimarket.repository.UserReviewRepository;

import java.time.LocalDateTime;

@Service
@Transactional
public class UserReviewService {

    @Autowired
    private UserReviewRepository reviewRepository;
    
    @Autowired
    ModelMapper mapper;

    @Autowired
    private UserRepository userRepository;

    public ReviewRequestDto submitReview(Long userId, ReviewRequestDto reviewDto) {
    	Integer rating = reviewDto.getRating();
        User user = userRepository.findById(userId).orElseThrow();
        UserReview review = mapper.map(reviewDto, UserReview.class);
        if (rating == null || rating < 1 || rating > 5) {
            throw new CustomException("Rating must be between 1 and 5");
        }
        return mapper.map(review, ReviewRequestDto.class);
    }
}