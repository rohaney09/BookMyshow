package com.example.BookMyShow.Review.service.serviceimp;//package com.example.BookMyShow.Review.service.serviceimp;
//
//import com.example.BookMyShow.Booking.repository.BookingRepository;
//import com.example.BookMyShow.Review.dto.reviewrequestdto.ReviewRequestDto;
//import com.example.BookMyShow.Review.dto.reviewresponsedto.ReviewResponseDto;
//import com.example.BookMyShow.Review.model.Review;
//import com.example.BookMyShow.Review.repository.ReviewRepository;
//import com.example.BookMyShow.Review.service.ReviewService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//@RequiredArgsConstructor
//public class ReviewServiceImpl implements ReviewService {
//
//    private final ReviewRepository reviewRepository;
//    private final BookingRepository bookingRepository;
//
//    @Override
//    public ReviewResponseDto addReview(ReviewRequestDto dto) {
//
//        //STEP 1: Check if user has booked the movie
//        boolean hasBooked = bookingRepository
//                .existsByUser_UserIdAndMovieIdAndDeletedFalse(
//                        dto.getUserId(),
//                        dto.getMovieId()
//                );
//
//        if (!hasBooked) {
//            throw new RuntimeException("User cannot review without booking the movie");
//        }
//
//        // STEP 2: Prevent duplicate review
//        if (reviewRepository.existsByMovieIdAndUserId(dto.getMovieId(), dto.getUserId())) {
//            throw new RuntimeException("User has already reviewed this movie");
//        }
//
//        //STEP 3: Save review
//        Review review = new Review();
//        review.setMovieId(dto.getMovieId());
//        review.setUserId(dto.getUserId());
//        review.setRating(dto.getRating());
//        review.setComment(dto.getComment());
//        review.setCreatedAt(LocalDateTime.now());
//
//        Review saved = reviewRepository.save(review);
//
//        return mapToResponse(saved);
//    }
//
//    @Override
//    public List<ReviewResponseDto> getReviewsByMovie(String movieId) {
//        return reviewRepository.findByMovieId(movieId)
//                .stream()
//                .map(this::mapToResponse)
//                .collect(Collectors.toList());
//    }
//
//    private ReviewResponseDto mapToResponse(Review review) {
//        ReviewResponseDto dto = new ReviewResponseDto();
//        dto.setReviewId(review.getId());
//        dto.setMovieId(review.getMovieId());
//        dto.setUserId(review.getUserId());
//        dto.setRating(review.getRating());
//        dto.setComment(review.getComment());
//        dto.setCreatedAt(review.getCreatedAt());
//        return dto;
//    }
//}

import com.example.BookMyShow.Booking.repository.BookingRepository;
import com.example.BookMyShow.Review.dto.reviewrequestdto.ReviewRequestDto;
import com.example.BookMyShow.Review.dto.reviewresponsedto.ReviewResponseDto;
import com.example.BookMyShow.Review.model.Review;
import com.example.BookMyShow.Review.repository.ReviewRepository;
import com.example.BookMyShow.Review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final BookingRepository bookingRepository;

    @Override
    public ReviewResponseDto addReview(ReviewRequestDto dto) {

        // ✅ STEP 1: Check if user has booked the movie
        boolean hasBooked = bookingRepository
                .existsByUser_UserIdAndShow_MovieIdAndDeletedFalse(
                        dto.getUserId(),
                        dto.getMovieId()
                );

        if (!hasBooked) {
            throw new RuntimeException("User cannot review without booking the movie");
        }

        // ✅ STEP 2: Prevent duplicate review
        if (reviewRepository.existsByMovieIdAndUserId(dto.getMovieId(), dto.getUserId())) {
            throw new RuntimeException("User has already reviewed this movie");
        }

        // ✅ STEP 3: Save review
        Review review = new Review();
        review.setMovieId(dto.getMovieId());
        review.setUserId(dto.getUserId());
        review.setRating(dto.getRating());
        review.setComment(dto.getComment());
        review.setCreatedAt(LocalDateTime.now());

        Review saved = reviewRepository.save(review);

        return mapToResponse(saved);
    }

    @Override
    public List<ReviewResponseDto> getReviewsByMovie(String movieId) {
        return reviewRepository.findByMovieId(movieId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private ReviewResponseDto mapToResponse(Review review) {
        ReviewResponseDto dto = new ReviewResponseDto();
        dto.setReviewId(review.getId());
        dto.setMovieId(review.getMovieId());
        dto.setUserId(review.getUserId());
        dto.setRating(review.getRating());
        dto.setComment(review.getComment());
        dto.setCreatedAt(review.getCreatedAt());
        return dto;
    }
}
