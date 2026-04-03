package com.example.BookMyShow.Theatre.service.serviceimp;



import com.example.BookMyShow.Theatre.service.TheatreService;
import com.example.BookMyShow.Theatre.entity.Theatre;
import com.example.BookMyShow.Theatre.dto.theatrerequestdto.TheatreRequestDto;
import com.example.BookMyShow.Theatre.dto.theatreresponsedto.TheatreResponseDto;
import com.example.BookMyShow.Theatre.repository.TheatreRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class TheatreServiceImpl implements TheatreService {

    private final TheatreRepository theatreRepository;
//  private final ShowRepository showRepository;
//
//    @Override
//    public String bookSeat(Long showId, String seatNumber) {
//
//        log.info("Attempting to book seat {} in theatre {}", seatNumber, showId);
//
//        Show show = showRepository.findById(showId)
//                .orElseThrow(() -> {
//                    log.error("Theatre not found with id {}", showId);
//                    return new RuntimeException("Theatre not found");
//                });
//
//        for (Seat seat : show.getSeats()) {
//
//            if (seat.getSeatNumber().equals(seatNumber)) {
//
//                if (seat.isBooked()) {
//                    log.warn("Seat {} already booked in theatre {}", seatNumber, showId);
//                    return "Seat already booked. Please choose another seat.";
//                }
//
//                seat.setBooked(true);
//
//                try {
//                    showRepository.save(show);
//                    log.info("Seat {} booked successfully in theatre {}", seatNumber, showId);
//                    return "Seat booked successfully!";
//                } catch (OptimisticLockingFailureException e) {
//                    log.warn("Optimistic locking failure while booking seat {} in theatre {}",
//                            seatNumber, showId);
//                    return "Seat was just booked by another user. Please try again.";
//                }
//            }
//        }
//
//        log.warn("Seat {} not found in theatre {}", seatNumber, theatreId);
//        return "Seat not found";
//    }

    @Override
    public Object createTheatre(TheatreRequestDto requestDto) {

        Theatre theatre = new Theatre();
        BeanUtils.copyProperties(requestDto, theatre);
        theatreRepository.save(theatre);

        return "Theatre created successfully!";
    }


    @Override
    public List<TheatreResponseDto> getAllTheatres() {

        log.info("Fetching all theatres");

        List<Theatre> theatres = theatreRepository.findAll();
        List<TheatreResponseDto> responseDtos = new ArrayList<>();

        for (Theatre theatre : theatres) {
            TheatreResponseDto dto = new TheatreResponseDto();
            BeanUtils.copyProperties(theatre, dto);
            responseDtos.add(dto);
        }

        log.info("Fetched {} theatres", responseDtos.size());
        return responseDtos;
    }

    @Override
    public TheatreResponseDto getTheatreById(String theatreId) {

        log.info("Fetching theatre by id {}", theatreId);

        Theatre theatre = theatreRepository.findById(theatreId)
                .orElseThrow(() -> {
                    log.error("Theatre not found with id {}", theatreId);
                    return new RuntimeException("Theatre not found");
                });

        TheatreResponseDto responseDto = new TheatreResponseDto();
        BeanUtils.copyProperties(theatre, responseDto);

        log.info("Theatre fetched successfully with id {}", theatreId);
        return responseDto;
    }

    @Override
    public Page<TheatreResponseDto> getTheatresByLocation(String locationId) {

        log.info("Fetching theatres for location {}", locationId);

        Pageable pageable = PageRequest.of(0, 10);

        Page<Theatre> theatres =
                theatreRepository.findByLocationId(locationId, pageable);

        log.info("Found {} theatres for location {}",
                theatres.getTotalElements(), locationId);

        return theatres.map(theatre -> {
            TheatreResponseDto dto = new TheatreResponseDto();
            BeanUtils.copyProperties(theatre, dto);
            return dto;
        });
    }

//    @Override
//    public List<Seat> getSeats(String theatreId) {
//
//        log.info("Fetching seats for theatre {}", theatreId);
//
//        Theatre theatre = theatreRepository.findById(theatreId)
//                .orElseThrow(() -> {
//                    log.error("Theatre not found with id {}", theatreId);
//                    return new RuntimeException("Theatre not found");
//                });
//
//        if (theatre.getSeats() == null || theatre.getSeats().isEmpty()) {
//            log.warn("Seat layout not configured for theatre {}", theatreId);
//            throw new RuntimeException("Seat layout not configured for this theatre");
//        }
//
//        log.info("Fetched {} seats for theatre {}", theatre.getSeats().size(), theatreId);
//        return theatre.getSeats();
//    }

    @Override
    public void deleteTheatre(String theatreId) {

        log.info("Deleting theatre with id {}", theatreId);

        Theatre theatre = theatreRepository.findById(theatreId)
                .orElseThrow(() -> {
                    log.error("Theatre not found with id {}", theatreId);
                    return new RuntimeException("Theatre not found");
                });

        theatreRepository.delete(theatre);

        log.info("Theatre deleted successfully with id {}", theatreId);
    }


//    @Override
//    public List<TheatreResponseDto> getTheatresByArea(String area) {
//
//        log.info("Fetching theatres by area={}", area);
//
//        List<Theatre> theatres = theatreRepository.findByArea(area);
//
//        List<TheatreResponseDto> responseDtos = new ArrayList<>();
//
//        for (Theatre theatre : theatres) {
//            TheatreResponseDto dto = new TheatreResponseDto();
//            BeanUtils.copyProperties(theatre, dto);
//            responseDtos.add(dto);
//        }
//
//        log.info("Found {} theatres in area={}", responseDtos.size(), area);
//        return responseDtos;
//    }


}
