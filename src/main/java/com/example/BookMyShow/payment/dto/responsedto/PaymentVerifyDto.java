package com.example.BookMyShow.payment.dto.responsedto;


import lombok.Data;

@Data
public class PaymentVerifyDto {
    private String razorpayOrderId;
    private String razorpayPaymentId;
    private String status;

}
