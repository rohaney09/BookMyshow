package com.example.BookMyShow.Notification.controller;

import com.example.BookMyShow.Notification.model.Notification;
import com.example.BookMyShow.Notification.service.NotificationService;
import com.example.BookMyShow.generic.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;


    //  Get Notifications
    @GetMapping("/getAllNoti")
    public ResponseEntity<CommonResponse<List<Notification>>> getAllNoti() {

        return ResponseEntity.ok(
                CommonResponse.success(
                        "Notification fetched successfully",
                        notificationService.getAllNotification()
                )
        );
    }
}
