package com.example.BookMyShow.Show;

import com.example.BookMyShow.Show.dto.showrequestdto.ShowRequestDto;
import com.example.BookMyShow.Show.dto.showresponsedto.ShowResponseDto;

import java.util.List;

public interface ShowService {

//    Show createShow(Show show);
//
//    List<Show> getShowsByMovie(String movieId);
//
//    List<Show> getShowsByTheatre(String theatreId);
//
//    Show getShowById(Long showId);
//
//    void deleteShow(Long showId);
ShowResponseDto addShow(ShowRequestDto dto);
    List<ShowResponseDto> getShowsByMovie(String movieId);
}

