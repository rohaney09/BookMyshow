package com.example.BookMyShow.generic.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(
        basePackages = {
                "com.example.BookMyShow.User.repository",
                "com.example.BookMyShow.Booking.repository",
                "com.example.BookMyShow.Show.repository",
                "com.example.BookMyShow.Payment.repository"}
)
public class MySqlConfig { }



