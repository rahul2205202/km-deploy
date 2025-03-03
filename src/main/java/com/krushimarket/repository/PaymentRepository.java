package com.krushimarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.krushimarket.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long>{
	
	Payment findByRazorpayOrderId(String razorpayOrderId);

}
