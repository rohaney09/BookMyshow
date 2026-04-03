package com.example.BookMyShow.Review.service;

import com.example.BookMyShow.Review.dto.reviewrequestdto.ReviewRequestDto;
import com.example.BookMyShow.Review.dto.reviewresponsedto.ReviewResponseDto;

import java.util.List;
    public interface ReviewService {

        ReviewResponseDto addReview(ReviewRequestDto dto);

        List<ReviewResponseDto> getReviewsByMovie(String movieId);
    }



