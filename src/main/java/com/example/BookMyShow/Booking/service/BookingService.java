package com.example.BookMyShow.Booking.service;

import com.example.BookMyShow.Booking.dto.bookingrequestdto.BookingRequestDto;
import com.example.BookMyShow.Booking.dto.bookingresponsedto.BookingResponseDto;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface BookingService {

BookingResponseDto createBooking(UUID userId, BookingRequestDto dto);

    void cancelBooking(Long bookingId);
}
