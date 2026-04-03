package com.example.BookMyShow.Review.dto.reviewresponsedto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data                        // Lombok: generates getters, setters, toString, etc.
    @NoArgsConstructor           // Default constructor
    @AllArgsConstructor          // Constructor with all fields
    @Builder                     // Enables builder pattern
    public class ReviewResponseDto {

        private String reviewId;
        private String movieId;
        private UUID userId;
        private Integer rating;
        private String comment;
        private LocalDateTime createdAt;
    }




