package com.example.BookMyShow.Location.dto.locationresponsedto;


import java.util.List;

import com.example.BookMyShow.Theatre.dto.theatreresponsedto.TheatreResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
public class LocationResponseDto {

    private String id;
    private String city;
    private String state;
}


