package com.example.BookMyShow.Notification.repository;


import com.example.BookMyShow.Notification.model.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends MongoRepository<Notification, String> {

}

