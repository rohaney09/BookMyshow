package com.example.BookMyShow.Booking.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Seat {

    private String seatNumber;
    private boolean booked;
    private Integer price;
}


