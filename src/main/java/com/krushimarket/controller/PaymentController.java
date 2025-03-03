package com.krushimarket.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.krushimarket.dto.PaymentRequestDto;
import com.krushimarket.dto.PaymentVerificationRequest;
import com.krushimarket.model.Payment;
import com.krushimarket.service.PaymentService;
import com.razorpay.RazorpayException;

@RestController
@RequestMapping("/payment")
public class PaymentController {
	@Autowired
	PaymentService service;
	
	@Autowired
	ModelMapper mapper;
	
	@PostMapping("/{userId}/do")
    public ResponseEntity<?> processPayment(@PathVariable Long userId,
                                                 @RequestBody PaymentRequestDto paymentRequest) {
        try {
        	System.out.println();
            Payment payment = mapper.map(paymentRequest, Payment.class);
            service.processPayment(userId, paymentRequest);
            return ResponseEntity.ok(payment);
        } catch (Exception e) {
        	System.out.println(e.getMessage());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
	@PostMapping("/verify")
    public ResponseEntity<String> verifyPayment(@RequestBody PaymentVerificationRequest paymentVerificationRequest) throws RazorpayException {
		try {
			service.verifyPayment(paymentVerificationRequest);
	        return ResponseEntity.ok("Payment Verified");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return ResponseEntity.internalServerError().body(e.getMessage());
		}
    }
}
