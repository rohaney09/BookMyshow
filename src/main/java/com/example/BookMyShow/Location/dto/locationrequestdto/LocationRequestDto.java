package com.example.BookMyShow.Location.dto.locationrequestdto;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.Indexed;



@Getter
@Setter
public class LocationRequestDto {

    @NotBlank(message = "City name cannot be empty")
    private String city;

    @NotBlank(message = "State cannot be empty")
    private String state;
}




