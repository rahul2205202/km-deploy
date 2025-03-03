package com.krushimarket.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.krushimarket.dto.SubscriptionRequestDto;
import com.krushimarket.exception.CustomException;
import com.krushimarket.model.Subscription;
import com.krushimarket.model.SubscriptionStatus;
import com.krushimarket.model.SubscriptionType;
import com.krushimarket.model.User;
import com.krushimarket.repository.SubscriptionRepository;
import com.krushimarket.repository.UserRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Service
@Transactional
public class SubscriptionService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SubscriptionRepository subscriptionRepository;
    
    @Autowired
    ModelMapper mapper;

    public SubscriptionRequestDto subscribeUser(Long userId, SubscriptionRequestDto subscriptionRequestDto) {
        User user = userRepository.findById(userId).orElseThrow();
        if (user.getSubscription() != null &&
            (user.getSubscription().getStatus() == SubscriptionStatus.Active ||
             (user.getSubscription().getEndDate() != null && user.getSubscription().getEndDate().isAfter(LocalDate.now())))) {
            throw new CustomException("User already has an active subscription");
        }

        Subscription subscription = new Subscription();
        subscription.setUser(user);
        subscription.setSubscriptionType(subscriptionRequestDto.getSubscriptionType());
        subscription.setStartDate(LocalDate.now());
        subscription.setPrice(subscriptionRequestDto.getPrice());
        subscription.setStatus(SubscriptionStatus.Active);
        subscription.setEndDate(LocalDate.now().plusDays(30));

        user.setSubscription(subscription);
        return mapper.map(subscription, SubscriptionRequestDto.class);
    }
    
    public boolean getSubscription(Long userId) {
    	User user = userRepository.findById(userId).orElseThrow(()-> new CustomException("User not found"));
    	if(subscriptionRepository.findByUser(user)!=null) {
    		return true;
    	}
    	return false;
    }
}
