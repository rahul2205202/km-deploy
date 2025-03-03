package com.krushimarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.krushimarket.dto.ReviewRequestDto;
import com.krushimarket.service.UserReviewService;

@RestController
@RequestMapping("/reviews")
public class UserReviewController {

    @Autowired
    private UserReviewService reviewService;

    @PostMapping("/users/{userId}")
    public ResponseEntity<ReviewRequestDto> submitReview(@PathVariable Long userId,
                                                     @RequestBody ReviewRequestDto reviewRequest) {
        try {
            ReviewRequestDto review = reviewService.submitReview(userId, reviewRequest);
            return ResponseEntity.ok(review);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}