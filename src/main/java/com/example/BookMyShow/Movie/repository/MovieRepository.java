package com.example.BookMyShow.Movie.repository;

import com.example.BookMyShow.Movie.document.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends MongoRepository<Movie, String> {

    Optional<Movie> findByTitleAndLanguageAndDeletedFalse(String title, String language);

    List<Movie> findByDeletedFalse();

    List<Movie> findByLanguageAndDeletedFalse(String language);

    List<Movie> findByGenreAndDeletedFalse(String genre);
}
