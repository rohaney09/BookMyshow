package com.example.BookMyShow.Booking.dto.bookingresponsedto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class BookingResponseDto {

    private Long bookingId;
    private Long showId;
    private String movieId;
    private String theatreId;
    private List<String> seatNumbers;
    private Double totalAmount;
    private String status;
    private LocalDateTime bookedAt;
}
