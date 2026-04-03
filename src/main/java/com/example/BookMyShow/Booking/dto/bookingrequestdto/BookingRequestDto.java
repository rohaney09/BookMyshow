package com.example.BookMyShow.Booking.dto.bookingrequestdto;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class BookingRequestDto {

    private Long showId;
    private List<String> seatNumbers;
}

