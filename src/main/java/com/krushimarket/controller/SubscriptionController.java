package com.krushimarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.krushimarket.dto.SubscriptionRequestDto;
import com.krushimarket.dto.UserDto;
import com.krushimarket.model.Subscription;
import com.krushimarket.model.User;
import com.krushimarket.service.SubscriptionService;

@RestController
@RequestMapping("/subscription")
public class SubscriptionController {

    @Autowired
    private SubscriptionService subscriptionService;

    @PostMapping("/{userId}/subscribe")
    public ResponseEntity<?> subscribeUser(@PathVariable Long userId,
                                              @RequestBody SubscriptionRequestDto subscriptionRequestDto) {
        try {
            SubscriptionRequestDto requestDto = subscriptionService.subscribeUser(userId, subscriptionRequestDto);
            return ResponseEntity.ok(requestDto);
        } catch (Exception e) {
            System.err.println("Error subscribing user: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    
    @GetMapping("/find")
    public ResponseEntity<?> findSubs(Long userId){
    	try {
//            Subscription subscription = subscriptionService.getSubscription(userId);
            return ResponseEntity.ok(subscriptionService.getSubscription(userId));
        } catch (Exception e) {
            System.err.println("Error subscribing user: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}