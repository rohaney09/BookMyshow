package com.example.BookMyShow.Movie.service;


import com.example.BookMyShow.Movie.document.Movie;
import com.example.BookMyShow.Movie.dto.movierequestdto.MovieRequestDto;

import java.util.List;

public interface MovieService {

    Movie addMovie(MovieRequestDto movie);   // idempotent

    List<Movie> getAllMovies();

    Movie getMovieById(String movieId);

    List<Movie> getMoviesByLanguage(String language);

    List<Movie> getMoviesByGenre(String genre);

    void deleteMovie(String movieId); // soft delete
    byte[] getMovieImage(String id);
}
