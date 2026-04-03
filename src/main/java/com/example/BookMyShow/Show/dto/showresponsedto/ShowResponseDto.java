package com.example.BookMyShow.Show.dto.showresponsedto;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ShowResponseDto {

    private Long showId;
    private String movieId;
    private String theatreId;
    private LocalDateTime showTime;
    private Integer totalSeats;
    private Integer availableSeats;
}

