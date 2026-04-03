package com.example.BookMyShow.Theatre.dto.theatreresponsedto;


import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TheatreResponseDto {

  //  private String theatreId;

    private String id;
    private String name;
    private String locationId;
    private int totalSeats;


}

