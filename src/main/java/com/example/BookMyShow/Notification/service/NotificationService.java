package com.example.BookMyShow.Notification.service;


import com.example.BookMyShow.Movie.document.Movie;
import com.example.BookMyShow.Notification.model.Notification;

import java.util.List;

public interface NotificationService {
    void notifyNewMovie(Movie movie);
    List<Notification> getAllNotification();
}

