package com.example.BookMyShow.Review.controller;
/*

import com.example.BookMyShow.Review.dto.reviewrequestdto.ReviewRequestDto;
import com.example.BookMyShow.Review.dto.reviewresponsedto.ReviewResponseDto;
import com.example.BookMyShow.Review.service.ReviewService;
import com.example.BookMyShow.generic.CommonResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

    @RestController
    @RequestMapping("/api/reviews")
    @RequiredArgsConstructor
    public class ReviewController {

        private final ReviewService reviewService;


        @PostMapping
        public ResponseEntity<CommonResponse<ReviewResponseDto>> addReview(@Valid @RequestBody ReviewRequestDto dto) {


            ReviewResponseDto response = reviewService.addReview(dto);

            return ResponseEntity.ok(
                    new CommonResponse(false,"Review created successfully", response)
            );

        }


        @PutMapping("/{reviewId}")
        public ResponseEntity<ReviewResponseDto> updateReview(
                @PathVariable String reviewId,              // path variable from URL
                @Valid @RequestBody ReviewRequestDto dto           // request body JSON
        ) {
            ReviewResponseDto updated = reviewService.updateReview(reviewId, dto);
            return ResponseEntity.ok(updated);
        }


        @DeleteMapping("/{reviewId}")
        public ResponseEntity<CommonResponse<Object>> deleteReview(@PathVariable String reviewId) {
            reviewService.deleteReview(reviewId);
            return ResponseEntity.ok(
                    new CommonResponse(false,"Review deleted successfully", null)
            );// HTTP 204
        }

        @GetMapping("/{reviewId}")

        public ResponseEntity<CommonResponse<ReviewResponseDto>> getReviewById(@PathVariable String reviewId) {
            return ResponseEntity.ok( new CommonResponse(false,"Review fetched successfully", reviewService.getReviewById(reviewId))
            );
        }


        @GetMapping("/movie/{movieId}")
        public ResponseEntity<CommonResponse> getReviewsByMovie(@PathVariable String movieId) {
            return ResponseEntity.ok(new CommonResponse(false,"movies are fetched",reviewService.getReviewsByMovieId(movieId)));
        }


        @GetMapping("/user/{userId}")
        public ResponseEntity<CommonResponse> getReviewsByUser(@PathVariable String userId) {
            return ResponseEntity.ok(new CommonResponse(false,"users is fetched",reviewService.getReviewsByUserId(userId)));
        }
    }


*/


import com.example.BookMyShow.Review.dto.reviewrequestdto.ReviewRequestDto;
import com.example.BookMyShow.Review.dto.reviewresponsedto.ReviewResponseDto;
import com.example.BookMyShow.Review.service.ReviewService;
import com.example.BookMyShow.generic.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    public CommonResponse<ReviewResponseDto> addReview(
            @RequestBody ReviewRequestDto dto
    ) {
        return CommonResponse.success(
                "Review added successfully",
                reviewService.addReview(dto)
        );
    }

    @GetMapping("/movie/{movieId}")
    public CommonResponse<List<ReviewResponseDto>> getReviewsByMovie(
            @PathVariable String movieId
    ) {
        return CommonResponse.success(
                "Reviews fetched",
                reviewService.getReviewsByMovie(movieId)
        );
    }
}
