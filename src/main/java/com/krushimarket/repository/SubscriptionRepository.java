package com.krushimarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.krushimarket.model.Subscription;
import com.krushimarket.model.User;

import java.util.List;
import java.util.Optional;


public interface SubscriptionRepository extends JpaRepository<Subscription, Long>{
	Subscription findByUser(User user);
}
