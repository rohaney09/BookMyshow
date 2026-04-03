package com.example.BookMyShow.Review.repository;
import com.example.BookMyShow.Review.model.Review;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
    public interface ReviewRepository extends MongoRepository<Review, String> {

        List<Review> findByMovieId(String movieId);

        boolean existsByMovieIdAndUserId(String movieId, UUID userId);
    }


