package com.example.BookMyShow.Movie.service.serviceimp;

import com.example.BookMyShow.Movie.document.Movie;
import com.example.BookMyShow.Movie.dto.movierequestdto.MovieRequestDto;
import com.example.BookMyShow.Movie.repository.MovieRepository;
import com.example.BookMyShow.Movie.service.MovieService;
import com.example.BookMyShow.Notification.service.NotificationService;
import com.example.BookMyShow.generic.exception.DataNotFound;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final NotificationService notificationService;

//    @Override
//    @Transactional
//    public Movie addMovie(MovieRequestDto movie) {
//
//        Movie existingMovie = movieRepository.findByTitleAndLanguageAndDeletedFalse(movie.getTitle(), movie.getLanguage()).orElse(null);
//
//        if (existingMovie != null) {
//            return existingMovie;
//        }
//        Movie movie1 = new Movie();
//        movie.setTitle(movie.getTitle());
//        movie.setLanguage(movie.getLanguage());
//        movie.setGenres(movie.getGenres());
//        movie.setDurationInMinutes(requestDto.getDurationInMinutes());
//        movie.setPostUrl(requestDto.getPostUrl());
//
//        Movie savedMovie = movieRepository.save(movie);
//        notificationService.notifyNewMovie(movie);
//        return savedMovie;
//    }

//@Override
//@Transactional
//public Movie addMovie(MovieRequestDto movie) {
//    Movie existingMovie = movieRepository.findByTitleAndLanguageAndDeletedFalse(movie.getTitle(), movie.getLanguage()).orElse(null);
//
//    if (existingMovie != null) {
//        return existingMovie;
//    }
//    Movie movie1 = new Movie();
//    movie1.setTitle(movie.getTitle());
//    movie1.setLanguage(movie.getLanguage());
//    movie1.setDurationMinutes(movie.getDurationInMinutes());
//    movie1.setPosterUrl(movie.getPosterUrl());
//    movie1.setGenre(movie.getGenre());
//    movie1.setCreatedAt(LocalDateTime.now());
//    if (movie.getPoster() != null && !movie.getPoster().isEmpty()) {
//        try {
//            String base64Image = Base64.getEncoder().encodeToString(movie.getPoster().getBytes());
//            movie1.setPoster(base64Image);
//        } catch (Exception e) {
//            throw new RuntimeException("Failed to store image", e);
//        }
//    }
//    Movie savedMovie = movieRepository.save(movie1);
//    notificationService.notifyNewMovie(movie1);
//
//    return savedMovie;
//}
//
//    //Download movie image
//    @Override
//    public byte[] getMovieImage(String id) {
//        Movie movie = movieRepository.findById(id).orElseThrow(() -> new DataNotFound("not found"));
//        if (movie.getPoster() == null) {
//            throw new RuntimeException("No image found for this movie");
//        }
//        return Base64.getDecoder().decode(movie.getPoster());
//    }

    @Override
    @Transactional
    public Movie addMovie(MovieRequestDto movie) {

        boolean exists = movieRepository
                .findByTitleAndLanguageAndDeletedFalse(
                        movie.getTitle(),
                        movie.getLanguage()
                )
                .isPresent();

        if (exists) {
            throw new RuntimeException("Movie already exists with same title and language");
        }

        Movie movieEntity = new Movie();
        movieEntity.setTitle(movie.getTitle());
        movieEntity.setLanguage(movie.getLanguage());
        movieEntity.setGenre(movie.getGenre());
        movieEntity.setDurationMinutes(movie.getDurationInMinutes());
        movieEntity.setCreatedAt(LocalDateTime.now());

        // Poster URL (if coming from frontend)
        movieEntity.setPosterUrl(movie.getPosterUrl());

        // Poster file (Base64)
        if (movie.getPoster() != null && !movie.getPoster().isEmpty()) {
            try {
                String base64Image = Base64.getEncoder()
                        .encodeToString(movie.getPoster().getBytes());
                movieEntity.setPoster(base64Image);
            } catch (IOException e) {
                throw new RuntimeException("Failed to process poster image", e);
            }
        }

        Movie savedMovie = movieRepository.save(movieEntity);

        // Notify AFTER save
        notificationService.notifyNewMovie(savedMovie);

        return savedMovie;
    }

    //Download movie image
    @Override
    public byte[] getMovieImage(String id) {
        Movie movie = movieRepository.findById(id).orElseThrow(() -> new DataNotFound("not found"));
        if (movie.getPoster() == null) {
            throw new RuntimeException("No image found for this movie");
        }
        return Base64.getDecoder().decode(movie.getPoster());
    }

    @Override
    public List<Movie> getAllMovies() {
        return movieRepository.findByDeletedFalse();
    }

    @Cacheable(value = "movies", key = "#movieId")
    @Override
    public Movie getMovieById(String movieId) {
        return movieRepository.findById(movieId)
                .filter(movie -> !movie.isDeleted())
                .orElseThrow(() -> new DataNotFound("Movie not found"));
    }

    @Override
    public List<Movie> getMoviesByLanguage(String language) {
        return movieRepository.findByLanguageAndDeletedFalse(language);
    }

    @Override
    public List<Movie> getMoviesByGenre(String genre) {

        return movieRepository.findByGenreAndDeletedFalse(genre);
    }

    @Override
    @Transactional
    public void deleteMovie(String movieId) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new DataNotFound("Movie not found"));

        movie.setDeleted(true);
        movieRepository.save(movie);
    }
}
