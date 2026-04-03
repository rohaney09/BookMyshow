package com.example.BookMyShow.Show.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "shows",
        indexes = {
                @Index(name = "idx_show_movie", columnList = "movieId"),
                @Index(name = "idx_show_theatre", columnList = "theatreId")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Show {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long showId;

    // MongoDB Movie _id
    @Column(nullable = false)
    private String movieId;

    // MongoDB Theatre _id
    @Column(nullable = false)
    private String theatreId;

    @Column(nullable = false)
    private LocalDateTime showTime;

    private Double pricePerSeat;
    @Column(nullable = false)
    private Integer totalSeats;

    @Column(nullable = false)
    private Integer availableSeats;

    @Column(nullable = false)
    private boolean deleted = false;

    @Column(nullable = false)
    private LocalDateTime createdAt;
}
