package com.example.BookMyShow.Review.dto.reviewrequestdto;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public class ReviewRequestDto {

        @NotBlank(message = "UserId is required")
        // Id of user who submits the review
        private UUID userId;

        @NotBlank(message = "MovieId is required")
        // Id of movie being reviewed (can switch to showId if needed)
        private String movieId;

        @NotNull(message = "Rating is required")
        @Min(value = 1, message = "Rating must be at least 1")
        @Max(value = 5, message = "Rating cannot be more than 5")
        // Numeric rating value (commonly 1 to 5)
        private Integer rating;

        @Size(max = 200, message = "Comment cannot exceed 200 characters")
        // Text comment provided by the user
        private String comment;

    }


