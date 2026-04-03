package com.example.BookMyShow.Theatre.controller;

import com.example.BookMyShow.Theatre.dto.theatrerequestdto.TheatreRequestDto;
import com.example.BookMyShow.Theatre.service.TheatreService;
import com.example.BookMyShow.generic.CommonResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.*;

    @RestController
    @RequestMapping("/Threatre")
    @Validated
    @AllArgsConstructor
    public class TheatreController {

        private TheatreService theatreService;

//        @PostMapping("/{theatreId}/book-seat/{seatNumber}")
//        public ResponseEntity<CommonResponse> bookSeat(@PathVariable String theatreId, @PathVariable String seatNumber) {
//
//            String result = theatreService.bookSeat(theatreId, seatNumber);
//
//            return ResponseEntity.ok(new CommonResponse(false, "Seat booking attempt completed", result
//                    )
//            );
//        }

        @PostMapping("/createTheatre")
        public ResponseEntity<CommonResponse> createTheatre(@Valid @RequestBody TheatreRequestDto requestDto) {

            return ResponseEntity.ok(
                    new CommonResponse(false,
                            "Theatre created successfully",
                            theatreService.createTheatre(requestDto)));
        }

        @GetMapping("/getalltheatres")
        public ResponseEntity<CommonResponse> getAllTheatres() {

            return ResponseEntity.ok(
                    new CommonResponse(false,
                            "All theatres fetched successfully",
                            theatreService.getAllTheatres()));
        }

        @GetMapping("/{theatreId}")
        public ResponseEntity<CommonResponse> getTheatreById(
                @PathVariable String theatreId) {

            return ResponseEntity.ok(
                    new CommonResponse(false,
                            "Theatre fetched successfully",
                            theatreService.getTheatreById(theatreId)));
        }


        @GetMapping("/location/{locationId}")
        public ResponseEntity<CommonResponse> getTheatresByLocation(
                @PathVariable String locationId) {

            return ResponseEntity.ok(
                    new CommonResponse(false, "Fetched",
                            theatreService.getTheatresByLocation(locationId))
            );
        }


//        @GetMapping("/{theatreId}/seats")
//        public ResponseEntity<CommonResponse> getSeats(
//                @PathVariable String theatreId) {
//
//            return ResponseEntity.ok(
//                    new CommonResponse(
//                            false,
//                            "Seat layout fetched successfully",
//                            theatreService.getSeats(theatreId)
//                    )
//            );
//        }


        @DeleteMapping("/{theatreId}")
        public ResponseEntity<CommonResponse> deleteTheatre(
                @PathVariable String theatreId) {

            theatreService.deleteTheatre(theatreId);

            return ResponseEntity.ok(
                    new CommonResponse(
                            false,
                            "Theatre deleted successfully",
                            null
                    )
            );
        }
//
//
//        @GetMapping("/area/{area}")
//        public ResponseEntity<CommonResponse> getTheatresByArea(
//                @PathVariable String area) {
//
//            return ResponseEntity.ok(
//                    new CommonResponse(
//                            false,
//                            "Theatres fetched successfully by area",
//                            theatreService.getTheatresByArea(area)
//                    )
//            );
//        }


    }


