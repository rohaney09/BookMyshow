package com.example.BookMyShow.Theatre.dto.theatrerequestdto;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.Indexed;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TheatreRequestDto {

    @NotBlank(message = "Theatre name cannot be empty")
    private String name;

    @NotBlank(message = "Location ID cannot be empty")
    private String locationId;

    @Min(value = 1, message = "Total seats must be at least 1")
    private int totalSeats;
}



