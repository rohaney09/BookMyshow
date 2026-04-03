package com.example.BookMyShow.Booking.controller;

import com.example.BookMyShow.Booking.dto.bookingrequestdto.BookingRequestDto;
import com.example.BookMyShow.Booking.dto.bookingresponsedto.BookingResponseDto;
import com.example.BookMyShow.Booking.service.BookingService;
import com.example.BookMyShow.generic.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/{userId}")
    public CommonResponse<BookingResponseDto> createBooking(
            @PathVariable UUID userId,
            @RequestBody BookingRequestDto dto
    ) {
        return CommonResponse.success(
                "Booking successful",
                bookingService.createBooking(userId, dto)
        );
    }

//    @GetMapping("/user/{userId}")
//    public CommonResponse<List<BookingResponseDto>> getBookingsByUser(
//            @PathVariable UUID userId
//    ) {
//        return CommonResponse.success(
//                "Bookings fetched",
//                bookingService.getBookingsByUser(userId)
//        );
//    }

    @DeleteMapping("/{bookingId}")
    public CommonResponse<String> cancelBooking(@PathVariable Long bookingId) {
        bookingService.cancelBooking(bookingId);
        return CommonResponse.success("Booking cancelled", null);
    }
}
