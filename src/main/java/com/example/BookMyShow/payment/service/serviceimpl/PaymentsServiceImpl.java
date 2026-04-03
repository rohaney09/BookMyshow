package com.example.BookMyShow.payment.service.serviceimpl;

import com.example.BookMyShow.Booking.repository.BookingRepository;
import com.example.BookMyShow.generic.exception.DataNotFound;
import com.example.BookMyShow.payment.repository.PaymentsRepository;
import com.example.BookMyShow.payment.service.PaymentsService;
import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PaymentsServiceImpl implements PaymentsService {

    @Autowired
    private APIContext apiContext;
    //private PaymentsRepository paymentsRepository;
    private BookingRepository bookingRepository;

    @Override
    public Payment createPayment(Double total, String currency, String method, String intent, String description, String cancelUrl, String successUrl) {
        Amount amount = new Amount();
        amount.setCurrency(currency);
        amount.setTotal(String.format("%.2f", total));

        Transaction transaction = new Transaction();
        transaction.setDescription(description);
        transaction.setAmount(amount);

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);

        Payer payer = new Payer();
        payer.setPaymentMethod(method);

        Payment payment = new Payment();
        payment.setIntent(intent);
        payment.setPayer(payer);
        payment.setTransactions(transactions);

        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl(cancelUrl);
        redirectUrls.setReturnUrl(successUrl);
        payment.setRedirectUrls(redirectUrls);

        try {
            return payment.create(apiContext);

        } catch (PayPalRESTException e) {
            throw new DataNotFound("Error creating PayPal payment");
        }
    }

    @Override
    public Payment executePayment(String paymentId, String payerId) {
        Payment payment = new Payment();
        payment.setId(paymentId);

        PaymentExecution execution = new PaymentExecution();
        execution.setPayerId(payerId);

        try {
            return payment.execute(apiContext, execution);
        } catch (PayPalRESTException e) {
            throw new RuntimeException("Error executing PayPal payment", e);
        }
    }
}

