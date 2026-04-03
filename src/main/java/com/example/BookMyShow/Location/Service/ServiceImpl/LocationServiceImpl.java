package com.example.BookMyShow.Location.Service.ServiceImpl;

import com.example.BookMyShow.Location.Service.LocationService;
import com.example.BookMyShow.Location.dto.locationrequestdto.LocationRequestDto;
import com.example.BookMyShow.Location.dto.locationresponsedto.LocationResponseDto;
import com.example.BookMyShow.Location.entity.Location;
import com.example.BookMyShow.Location.repository.LocationRepository;
import com.example.BookMyShow.Theatre.entity.Theatre;
import com.example.BookMyShow.Theatre.dto.theatreresponsedto.TheatreResponseDto;
import com.example.BookMyShow.Theatre.repository.TheatreRepository;
import com.example.BookMyShow.generic.exception.DataNotFound;
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
    public class LocationServiceImpl implements LocationService {

        private final LocationRepository locationRepository;
        private final TheatreRepository theatreRepository;

        @Override
        public Object createLocation(LocationRequestDto locationRequestDto) {

            log.info("Creating location with ");

            Location location = new Location();
            BeanUtils.copyProperties(locationRequestDto, location);
            locationRepository.save(location);

            log.info("Location created successfully with id={}");
            return "Location created";
        }

        @Override
        public List<LocationResponseDto> getAllLocations() {

            log.info("Fetching all locations");

            List<Location> all = locationRepository.findAll();
            List<LocationResponseDto> locationResponseDtos = new ArrayList<>();

            for (Location location : all) {
                LocationResponseDto dto = new LocationResponseDto();
                BeanUtils.copyProperties(location, dto);
                locationResponseDtos.add(dto);
            }

            log.info("Fetched {} locations", locationResponseDtos.size());
            return locationResponseDtos;
        }

        @Override
        public LocationResponseDto getLocationById(String id) {

            log.info("Fetching location by id={}", id);

            Location location = locationRepository.findById(id)
                    .orElseThrow(() -> {
                        log.error("Location not found with id={}", id);
                        return new DataNotFound("Location not found");
                    });

            LocationResponseDto responseDto = new LocationResponseDto();
            BeanUtils.copyProperties(location, responseDto);

            log.info("Location fetched successfully with id={}", id);
            return responseDto;
        }

        @Override
        public void deleteLocation(String id) {

            log.info("Deleting location with id={}", id);

            if (!locationRepository.existsById(id)) {
                log.error("Attempted to delete non-existing location with id={}", id);
                throw new DataNotFound("Location not found");
            }

            locationRepository.deleteById(id);
            log.info("Location deleted successfully with id={}", id);
        }

        @Override
        public LocationResponseDto getLocationWithTheatres(String id) {

            log.info("Fetching location with theatres for locationId={}", id);

            Location location = locationRepository.findById(id)
                    .orElseThrow(() -> {
                        log.error("Location not found with id={}", id);
                        return new DataNotFound("Location not found");
                    });

            Pageable pageable = PageRequest.of(0, 10);
            Page<Theatre> theatres =
                    theatreRepository.findByLocationId(id, pageable);

            log.info("Found {} theatres for locationId={}",
                    theatres.getTotalElements(), id);

            List<TheatreResponseDto> theatreDtos = new ArrayList<>();

            for (Theatre theatre : theatres) {
                TheatreResponseDto dto = new TheatreResponseDto();
                BeanUtils.copyProperties(theatre, dto);
                theatreDtos.add(dto);
            }

            LocationResponseDto locationResponseDto = new LocationResponseDto();
            BeanUtils.copyProperties(location, locationResponseDto);

            log.info("Location with theatres response prepared for locationId={}", id);
            return locationResponseDto;
        }
    }


