package com.example.BookMyShow.Show.dto.showrequestdto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ShowRequestDto {

    @NotBlank(message = "Movie ID cannot be empty")
    private String movieId;

    @NotBlank(message = "Theatre ID cannot be empty")
    private String theatreId;

    @NotNull(message = "Show time cannot be null")
    private LocalDateTime showTime;

    private Integer totalSeats;

    private Double pricePerSeat;
}

