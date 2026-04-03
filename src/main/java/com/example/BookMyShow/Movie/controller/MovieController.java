package com.example.BookMyShow.Movie.controller;



import com.example.BookMyShow.Movie.document.Movie;
import com.example.BookMyShow.Movie.dto.movierequestdto.MovieRequestDto;
import com.example.BookMyShow.Movie.service.MovieService;
import com.example.BookMyShow.generic.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;


//    @PostMapping
//    public CommonResponse<Movie> addMovie(@RequestBody MovieRequestDto movie) {
//        return CommonResponse.success(
//                "Movie added successfully",
//                movieService.addMovie(movie)
//        );
//    }


    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public CommonResponse<Movie> addMovie(@ModelAttribute MovieRequestDto movie) {
        Movie savedMovie = movieService.addMovie(movie);

        return CommonResponse.success(
                "Movie added successfully",
                savedMovie
        );
    }


    //Image download
    @GetMapping(value = "/downloadImage/{id}", produces = {"image/jpeg", "image/png"})
    public ResponseEntity<byte[]> downloadMovieImage(@PathVariable String id) {
        byte[] imageBytes = movieService.getMovieImage(id);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG) //To display in swagger
                // .header("Content-Disposition", "attachment; filename=movie-image.jpg") //To download in local
                .body(imageBytes);
    }


    @GetMapping
    public CommonResponse<List<Movie>> getAllMovies() {
        return CommonResponse.success(
                "Movies fetched successfully",
                movieService.getAllMovies()
        );
    }


    @GetMapping("/{movieId}")
    public CommonResponse<Movie> getMovieById(@PathVariable String movieId) {
        return CommonResponse.success(
                "Movie fetched successfully",
                movieService.getMovieById(movieId)
        );
    }


    @GetMapping("/language/{language}")
    public CommonResponse<List<Movie>> getMoviesByLanguage(@PathVariable String language) {
        return CommonResponse.success(
                "Movies fetched successfully",
                movieService.getMoviesByLanguage(language)
        );
    }

    @GetMapping("/genre/{genre}")
    public CommonResponse<List<Movie>> getMoviesByGenre(@PathVariable String genre) {
        return CommonResponse.success(
                "Movies fetched successfully",
                movieService.getMoviesByGenre(genre)
        );
    }

    @DeleteMapping("/{movieId}")
    public CommonResponse<String> deleteMovie(@PathVariable String movieId) {
        movieService.deleteMovie(movieId);
        return CommonResponse.success("Movie deleted successfully", null);
    }
}
