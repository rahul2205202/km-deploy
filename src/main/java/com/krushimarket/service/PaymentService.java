package com.krushimarket.service;

import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.krushimarket.dto.PaymentRequestDto;
import com.krushimarket.dto.PaymentVerificationRequest;
import com.krushimarket.dto.SubscriptionRequestDto;
import com.krushimarket.model.Payment;
import com.krushimarket.model.PaymentStatus;
import com.krushimarket.model.User;
import com.krushimarket.repository.PaymentRepository;
import com.krushimarket.repository.UserRepository;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SubscriptionService subscriptionService;

    @Autowired
    private ModelMapper mapper;

    private String razorpayKeyId = "";

    private String razorpayKeySecret = "";

    @Transactional
    public PaymentRequestDto processPayment(Long userId, PaymentRequestDto paymentRequestDto) throws RazorpayException {
        try {
        	System.out.println(paymentRequestDto.toString());
        	User user = userRepository.findById(userId).orElseThrow();
            Payment payment = new Payment();
            payment.setUser(user);
            payment.setAmount(paymentRequestDto.getAmount());
            payment.setPaymentDate(LocalDateTime.now());
            payment.setSubscriptionType(paymentRequestDto.getSubscriptionType());
            payment.setStatus(PaymentStatus.Pending);
            RazorpayClient razorpay = new RazorpayClient(razorpayKeyId, razorpayKeySecret);
            JSONObject options = new JSONObject();
            BigDecimal amountInPaise = paymentRequestDto.getAmount().multiply(new BigDecimal("100")); // Amount in paise
            options.put("amount", amountInPaise);
            options.put("currency", "INR");
            options.put("receipt", "order_rcptid_" + System.currentTimeMillis()); // Generate unique receipt ID
            options.put("notes", new JSONObject()); // Optional notes
            Order order = razorpay.Orders.create(options);
            String orderId = order.get("id");
            payment.setRazorpayOrderId(orderId);
            return mapper.map(payment, PaymentRequestDto.class);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
        return null;
    }


    public void verifyPayment(PaymentVerificationRequest paymentVerificationRequest) throws RazorpayException {
        try {
            RazorpayClient razorpay = new RazorpayClient(razorpayKeyId, razorpayKeySecret);

            String generatedSignature = generateSignature(
                    paymentVerificationRequest.getRazorpay_order_id(),
                    paymentVerificationRequest.getRazorpay_payment_id(),
                    razorpayKeySecret);

            if (generatedSignature.equals(paymentVerificationRequest.getRazorpay_signature())) {
                Payment payment = paymentRepository.findByRazorpayOrderId(paymentVerificationRequest.getOrderId());
                payment.setStatus(PaymentStatus.Success);
                paymentRepository.save(payment);

                SubscriptionRequestDto dto = new SubscriptionRequestDto();
                dto.setUserId(payment.getUser().getUserID());
                dto.setPrice(payment.getAmount());
                dto.setSubscriptionType(payment.getSubscriptionType());
                subscriptionService.subscribeUser(payment.getUser().getUserID(), dto);

            } else {
                throw new RazorpayException("Payment verification failed");
            }
        } catch (Exception e) {
            throw new RazorpayException("Payment verification failed");
        }
    }

    private String generateSignature(String razorpayOrderId, String razorpayPaymentId, String razorpaySecret) {
        String data = razorpayOrderId + "|" + razorpayPaymentId;
        try {
            javax.crypto.Mac mac = javax.crypto.Mac.getInstance("HmacSHA256");
            mac.init(new javax.crypto.spec.SecretKeySpec(razorpaySecret.getBytes(), "HmacSHA256"));
            return bytesToHex(mac.doFinal(data.getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
