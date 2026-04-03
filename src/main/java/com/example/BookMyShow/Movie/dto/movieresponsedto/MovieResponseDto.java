package com.example.BookMyShow.Movie.dto.movieresponsedto;



import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class MovieResponseDto {

    private String movieId;
    private String title;
    private String language;
    private String genre;
    private int durationInMinutes;
    private String poster;
    private String posterUrl;
}
