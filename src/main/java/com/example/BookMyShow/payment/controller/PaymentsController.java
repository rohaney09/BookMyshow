package com.example.BookMyShow.payment.controller;


import com.example.BookMyShow.Booking.model.Booking;
import com.example.BookMyShow.Booking.model.BookingStatus;
import com.example.BookMyShow.Booking.repository.BookingRepository;
import com.example.BookMyShow.email.EmailRequestDto;
import com.example.BookMyShow.email.EmailService;
import com.example.BookMyShow.email.EmailTemplateUtil;
import com.example.BookMyShow.payment.entity.PaymentTransaction;
import com.example.BookMyShow.payment.repository.PaymentsRepository;
import com.example.BookMyShow.payment.service.PaymentsService;
import com.example.BookMyShow.payment.service.TicketPdfGenerator;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/payment")
public class PaymentsController {

    @Autowired
    private PaymentsService paymentsService;

   // @Autowired
    //private PaymentsRepository paymentsRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private EmailService emailService;


    @PostMapping("/create/{bookingId}")
    public ResponseEntity<String> createPayment(@PathVariable Long bookingId) {

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        Double amount = booking.getTotalAmount();

        String cancelUrl = "http://localhost:8080/payment/cancel";
        String successUrl =
                "http://localhost:8080/payment/success?bookingId=" + bookingId;

        Payment payment = paymentsService.createPayment(
                amount,
                "USD",
                "paypal",
                "sale",
                "Movie Ticket Booking",
                cancelUrl,
                successUrl
        );

        for (Links link : payment.getLinks()) {
            if ("approval_url".equals(link.getRel())) {
                return ResponseEntity.ok(link.getHref());
            }
        }
        return ResponseEntity.badRequest().body("Payment creation failed");
    }


    @GetMapping("/success")
    public ResponseEntity<String> success(
            @RequestParam String paymentId,
            @RequestParam String PayerID,
            @RequestParam Long bookingId) throws Exception {

        // IDEMPOTENCY CHECK
        Optional<PaymentTransaction> existing =
                paymentsRepository.findById(paymentId);

        if (existing.isPresent()) {
            return ResponseEntity.ok("Payment already processed");
        }

        //  EXECUTE PAYPAL PAYMENT
        Payment paypalPayment =
                paymentsService.executePayment(paymentId, PayerID);

        if (!"approved".equals(paypalPayment.getState())) {
            return ResponseEntity.badRequest().body("Payment failed");
        }

        // SAVE PAYMENT TRANSACTION
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        PaymentTransaction tx = new PaymentTransaction();
        tx.setPaymentId(paymentId);
        tx.setPayerId(PayerID);
        tx.setStatus("APPROVED");
        tx.setAmount(booking.getTotalAmount());
        tx.setCreatedAt(LocalDateTime.now());
        paymentsRepository.save(tx);

        // CONFIRM BOOKING
        booking.setStatus(BookingStatus.CONFIRMED);
        bookingRepository.save(booking);

        // AFTER booking.setStatus(CONFIRMED)

        byte[] pdf = TicketPdfGenerator.generateTicketPdf(booking);

        String emailBody =
                "Hello " + booking.getUser().getName() + ",\n\n" +
                        "Your booking is confirmed \n" +
                        "Please find your ticket attached as PDF.\n\n" +
                        "Enjoy your movie ";

        emailService.sendEmailWithAttachment(
                booking.getUser().getEmail(),
                "Your Movie Ticket",
                emailBody,
                pdf,
                "MovieTicket_" + booking.getBookingCode() + ".pdf"
        );


        return ResponseEntity.ok("Payment Successful & Booking Confirmed ");
    }

    @GetMapping("/cancel")
    public ResponseEntity<String> cancel() {
        return ResponseEntity.ok("Payment Cancelled");
    }
}

