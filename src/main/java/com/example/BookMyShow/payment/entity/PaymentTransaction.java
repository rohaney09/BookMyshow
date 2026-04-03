package com.example.BookMyShow.payment.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "payment_transactions")
public class PaymentTransaction {

    @Id
    private String paymentId;
    private String payerId;
    private String status;
    private Double amount;
    private LocalDateTime createdAt;

}


