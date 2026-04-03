package com.example.BookMyShow.Theatre.service;


import com.example.BookMyShow.Theatre.dto.theatrerequestdto.TheatreRequestDto;
import com.example.BookMyShow.Theatre.dto.theatreresponsedto.TheatreResponseDto;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TheatreService {


    Object createTheatre(@Valid TheatreRequestDto requestDto);

    List<TheatreResponseDto> getAllTheatres();

    TheatreResponseDto getTheatreById(String theatreId);

    Page<TheatreResponseDto> getTheatresByLocation(String locationId);



    void deleteTheatre(String theatreId);


  //  List<TheatreResponseDto> getTheatresByArea(String area);
}

