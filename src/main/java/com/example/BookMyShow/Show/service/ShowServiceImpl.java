package com.example.BookMyShow.Show.service;


import com.example.BookMyShow.Show.ShowService;
import com.example.BookMyShow.Show.entity.Show;
import com.example.BookMyShow.Show.dto.showrequestdto.ShowRequestDto;
import com.example.BookMyShow.Show.dto.showresponsedto.ShowResponseDto;
import com.example.BookMyShow.Show.repository.ShowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShowServiceImpl implements ShowService {

    private final ShowRepository showRepository;

    @Override
    public ShowResponseDto addShow(ShowRequestDto dto) {

        Show show = new Show();
        show.setMovieId(dto.getMovieId());
        show.setTheatreId(dto.getTheatreId());
        show.setShowTime(dto.getShowTime());
        show.setTotalSeats(dto.getTotalSeats());
        show.setAvailableSeats(dto.getTotalSeats()); // initially all seats available
        show.setDeleted(false);
        show.setPricePerSeat(dto.getPricePerSeat());
        show.setCreatedAt(LocalDateTime.now());

        Show savedShow = showRepository.save(show);
        return mapToResponse(savedShow);
    }

    @Override
    public List<ShowResponseDto> getShowsByMovie(String movieId) {
        return showRepository.findByMovieIdAndDeletedFalse(movieId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private ShowResponseDto mapToResponse(Show show) {
        ShowResponseDto dto = new ShowResponseDto();
        dto.setShowId(show.getShowId());
        dto.setMovieId(show.getMovieId());
        dto.setTheatreId(show.getTheatreId());
        dto.setShowTime(show.getShowTime());
        dto.setTotalSeats(show.getTotalSeats());
        dto.setAvailableSeats(show.getAvailableSeats());
        return dto;
    }
}
