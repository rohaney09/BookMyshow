package com.example.BookMyShow.Theatre.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
//
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@Document(collection = "theatres")
//public class Theatre {
//
//    @Id
//    private String theatreId;
//
//    @Indexed
//    private String name;
//
//    @Indexed
//    private String area;
//
//    private int seatCount;
//
////    private List<Seat> seats;
//
////    @JsonIgnore
////    @Version
////    private Long version;
//
//    //mapping
//    @Indexed
//    private String locationId;
//
//
//}
@Document(collection = "theatres")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Theatre {

    @Id
    private String id;

    private String name;

    private String locationId;   // MongoDB Location _id

    private Integer totalSeats;

    private boolean deleted = false;
}
