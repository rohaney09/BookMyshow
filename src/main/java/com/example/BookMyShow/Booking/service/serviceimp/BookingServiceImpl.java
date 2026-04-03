package com.example.BookMyShow.Booking.service.serviceimp;

import com.example.BookMyShow.Booking.dto.bookingrequestdto.BookingRequestDto;
import com.example.BookMyShow.Booking.dto.bookingresponsedto.BookingResponseDto;
import com.example.BookMyShow.Booking.model.Booking;
import com.example.BookMyShow.Booking.model.BookingStatus;
import com.example.BookMyShow.Booking.repository.BookingRepository;
import com.example.BookMyShow.Booking.service.BookingService;
import com.example.BookMyShow.Show.entity.Show;
import com.example.BookMyShow.Show.repository.ShowRepository;
import com.example.BookMyShow.User.model.User;
import com.example.BookMyShow.User.repository.UserRepository;
import com.example.BookMyShow.generic.exception.DataNotFound;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final ShowRepository showRepository;


@Override
@Transactional
public BookingResponseDto createBooking(UUID userId, BookingRequestDto dto) {

    if (dto.getSeatNumbers() == null || dto.getSeatNumbers().isEmpty()) {
        throw new DataNotFound("Seat numbers are missing");
    }

    User user = userRepository.findById(userId)
            .orElseThrow(() -> new DataNotFound("User not found"));

    Show show = showRepository.findById(dto.getShowId())
            .orElseThrow(() -> new DataNotFound("Show not found"));

    int requestedSeats = dto.getSeatNumbers().size();

    if (show.getAvailableSeats() < requestedSeats) {
        throw new DataNotFound("Not enough seats available");
    }

    double ticketAmount = show.getPricePerSeat() * requestedSeats;

    show.setAvailableSeats(show.getAvailableSeats() - requestedSeats);
    showRepository.save(show);

    Booking booking = new Booking();
    booking.setUser(user);
    booking.setShow(show);
    booking.setSeatsBooked(dto.getSeatNumbers());
    booking.setTotalAmount(ticketAmount);
    booking.setStatus(BookingStatus.PENDING);
    booking.setBookingCode("BMS-" + UUID.randomUUID().toString().substring(0, 8));
    booking.setCreatedAt(LocalDateTime.now());
    booking.setUpdatedAt(LocalDateTime.now());

    return mapToResponse(bookingRepository.save(booking));
}



    @Override
    @Transactional
    public void cancelBooking(Long bookingId) {

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new DataNotFound("Booking not found"));

        if (booking.getStatus() == BookingStatus.CANCELLED) {
            return;
        }

        Show show = booking.getShow();

        booking.setStatus(BookingStatus.CANCELLED);
        booking.setDeleted(true);

        showRepository.save(show);
        bookingRepository.save(booking);
    }

    private BookingResponseDto mapToResponse(Booking booking) {
        BookingResponseDto dto = new BookingResponseDto();
        dto.setBookingId(booking.getBookingId());
        dto.setShowId(booking.getShow().getShowId());
        dto.setMovieId(booking.getShow().getMovieId());
        dto.setTheatreId(booking.getShow().getTheatreId());
       dto.setSeatNumbers(booking.getSeatsBooked());
        dto.setTotalAmount(booking.getTotalAmount());
        dto.setStatus(booking.getStatus().name());
        dto.setBookedAt(booking.getCreatedAt());
        return dto;
    }
}
