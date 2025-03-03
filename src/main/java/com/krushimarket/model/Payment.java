package com.krushimarket.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentID;

    @ManyToOne
    @JoinColumn(name = "userID")
    private User user;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;

    // PaymentMethod might not be strictly necessary with Razorpay
    // unless you use it for other payment methods as well.
    // @Enumerated(EnumType.STRING)
    // private PaymentMethod paymentMethod;  // Remove or keep as needed

    private LocalDateTime paymentDate;

    @Enumerated(EnumType.STRING)
    private SubscriptionType subscriptionType;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    @Column(name = "razorpay_order_id") // Add Razorpay Order ID field
    private String razorpayOrderId;

    // Constructors (No-args and All-args - Lombok can help with this)
    public Payment() {
    }

    public Payment(Long paymentID, User user, BigDecimal amount, LocalDateTime paymentDate,
                   SubscriptionType subscriptionType, PaymentStatus status, String razorpayOrderId) {
        this.paymentID = paymentID;
        this.user = user;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.subscriptionType = subscriptionType;
        this.status = status;
        this.razorpayOrderId = razorpayOrderId;
    }


    // Getters and Setters (Lombok can help with this)
    public Long getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(Long paymentID) {
        this.paymentID = paymentID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }


    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public SubscriptionType getSubscriptionType() {
        return subscriptionType;
    }

    public void setSubscriptionType(SubscriptionType subscriptionType) {
        this.subscriptionType = subscriptionType;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    public String getRazorpayOrderId() {
        return razorpayOrderId;
    }

    public void setRazorpayOrderId(String razorpayOrderId) {
        this.razorpayOrderId = razorpayOrderId;
    }

    //Removed PaymentMethod

}