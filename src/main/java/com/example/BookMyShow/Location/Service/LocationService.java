package com.example.BookMyShow.Location.Service;




import com.example.BookMyShow.Location.dto.locationrequestdto.LocationRequestDto;
import com.example.BookMyShow.Location.dto.locationresponsedto.LocationResponseDto;

import java.util.List;

    public interface LocationService {
        Object createLocation(LocationRequestDto locationRequestDto);

        List<LocationResponseDto> getAllLocations();

        LocationResponseDto getLocationById(String id);

        void deleteLocation(String id);

        LocationResponseDto getLocationWithTheatres(String id);
    }


