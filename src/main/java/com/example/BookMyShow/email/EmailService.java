package com.example.BookMyShow.email;

public interface EmailService {

    void sendEmailWithAttachment(
            String to,
            String subject,
            String body,
            byte[] attachment,
            String fileName
    );
}

