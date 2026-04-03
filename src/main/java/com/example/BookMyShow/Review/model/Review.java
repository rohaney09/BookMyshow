package com.example.BookMyShow.Review.model;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

//
//@Data
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
//@Document(collection = "reviews")
//public class Review {
//
//    @Id
//
//    private String id;
//
//
//    private String userId;
//
//
//    private String movieId;
//
//    // Rating given by the user (1 to 5 normally)
//    private Integer rating;
//
//    // Optional comment text from user
//    private String comment;
//
//    @CreatedDate
//    // Automatically stores date-time when document is inserted into MongoDB
//    private LocalDateTime createdAt;
//
//    @LastModifiedDate
//    // Automatically updates whenever the document is modified
//    private LocalDateTime updatedAt;
//}
//
@Document(collection = "reviews")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Review {

    @Id
    private String id;

    private String movieId;

    private UUID userId;

    private Integer rating;
    private String comment;

    private LocalDateTime createdAt;
}

