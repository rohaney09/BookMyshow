package com.example.BookMyShow.Notification.dto;

import java.time.LocalDateTime;

/*
 * NotificationResponseDto is used to SEND data to client.
 * We never expose Mongo document directly in API.
 */

import lombok.Getter;
import lombok.Setter;
import java.util.UUID;

@Getter
@Setter
public class NotificationResponseDto {
    private String id;
    private UUID userId;
    private String movieId;
    private String message;
    private LocalDateTime sentAt;
}
