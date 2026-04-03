package com.example.BookMyShow.Movie.dto.movierequestdto;


import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class MovieRequestDto {

    @NotBlank(message = "Movie title cannot be empty")
    private String title;

    @NotBlank(message = "Language cannot be empty")
    private String language;

    @NotBlank(message = "Genre cannot be empty")
    private String genre;

    private int durationInMinutes;
    private MultipartFile poster;
    private String posterUrl;
}
