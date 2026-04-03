package com.example.BookMyShow.Movie.document;

import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Document(collection = "movies")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Movie {

    @Id
    private String id;
    private String title;
    private String language;
    private String genre;
    private Integer durationMinutes;
    private boolean deleted = false;
    private LocalDateTime createdAt;
    private String poster;
    private String posterUrl;
}

