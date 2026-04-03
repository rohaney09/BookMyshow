package com.example.BookMyShow.generic.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(
        basePackages = {
                "com.example.BookMyShow.Movie.repository",
                "com.example.BookMyShow.Location.repository",
                "com.example.BookMyShow.Theatre.repository",
                "com.example.BookMyShow.Notification.repository",
                "com.example.BookMyShow.Review.repository"
        }
)
public class MongoConfig { }
