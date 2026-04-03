package com.example.BookMyShow.email;


import com.example.BookMyShow.Booking.model.Booking;
import com.example.BookMyShow.Show.entity.Show;
import com.example.BookMyShow.User.model.User;

public class EmailTemplateUtil {

    public static EmailRequestDto bookingConfirmation(Booking booking) {

        User user = booking.getUser();
        Show show = booking.getShow();

        String body =
                "Hello " + user.getName() + ",\n\n" +
                        "🎉 Your booking has been successfully confirmed!\n\n" +
                        "📌 Booking ID: " + booking.getBookingCode() + "\n" +
                        "🎥 Movie ID: " + show.getMovieId() + "\n" +
                        "🏛 Theatre ID: " + show.getTheatreId() + "\n" +
                        "🕒 Show Time: " + show.getShowTime() + "\n" +
                        "💺 Seats: " + booking.getSeatsBooked() + "\n\n" +
                        "💰 Total Amount: ₹" + booking.getTotalAmount() + "\n\n" +
                        "Enjoy your movie 🍿\n\n" +
                        "— Team BookMyShow";

        return new EmailRequestDto(
                user.getEmail(),
                "🎬 Booking Confirmed | BookMyShow",
                body,
                "BOOKING"
        );
    }
}



