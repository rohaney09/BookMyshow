package com.example.BookMyShow.payment.service;

import com.paypal.api.payments.Payment;

public interface PaymentsService {

    Payment createPayment(
            Double total,
            String currency,
            String method,
            String intent,
            String description,
            String cancelUrl,
            String successUrl
    );

    Payment executePayment(String paymentId, String payerId);
}

