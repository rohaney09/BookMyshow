package com.example.BookMyShow.Location.controller;

import com.example.BookMyShow.Location.Service.LocationService;
import com.example.BookMyShow.Location.dto.locationrequestdto.LocationRequestDto;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import com.example.BookMyShow.generic.CommonResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

    @RestController
    @RequestMapping("/Location")
    @Validated
    @AllArgsConstructor
    public class LocationController {

        private LocationService locationService;

        @PostMapping("/createlocation")
        public ResponseEntity<CommonResponse> createLocation(@Valid @RequestBody LocationRequestDto locationRequestDto) {
            return ResponseEntity.ok(new CommonResponse(false,"Location created ",locationService.createLocation(locationRequestDto)));

        }

        @GetMapping("/getAll")
        public ResponseEntity<CommonResponse> getAllLocations() {

            return ResponseEntity.ok(new CommonResponse(false, "All locations fetched successfully", locationService.getAllLocations()
                    )
            );

        }
        @GetMapping("/getById/{id}")
        public ResponseEntity<CommonResponse> getLocationById(@PathVariable String id) {

            return ResponseEntity.ok(new CommonResponse(false, "Location fetched successfully", locationService.getLocationById(id)
                    )
            );
        }

        @GetMapping("/getLocationWithTheatres/{id}")
        public ResponseEntity<CommonResponse> getLocationWithTheatres(@PathVariable String id) {

            return ResponseEntity.ok(new CommonResponse(false, "Location with theatres fetched successfully", locationService.getLocationWithTheatres(id)));
        }

        @DeleteMapping("/delete/{id}")
        public ResponseEntity<CommonResponse> deleteLocation(
                @PathVariable String id) {
            locationService.deleteLocation(id);

            return ResponseEntity.ok(new CommonResponse(false, "Location deleted successfully", null));
        }

    }



