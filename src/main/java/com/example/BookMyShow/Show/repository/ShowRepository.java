package com.example.BookMyShow.Show.repository;

import com.example.BookMyShow.Show.entity.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface ShowRepository extends JpaRepository<Show, Long> {

    List<Show> findByMovieIdAndDeletedFalse(String movieId);

}
