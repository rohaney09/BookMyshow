package com.example.BookMyShow.User.model;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(
        name = "users",
        indexes = {
                @Index(name = "idx_user_email", columnList = "email", unique = true)
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue
            //(strategy = GenerationType.UUID)
  //  @Column(columnDefinition = "CHAR(36)")
    private UUID userId;

    private String name;

    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    private boolean deleted = false;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
    @PrePersist
    void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}

//
//@Entity
//@Table(
//        name = "users",
//        indexes = {
//                @Index(name = "idx_user_email", columnList = "email", unique = true)
//        }
//)
//@Getter
//@Setter
//public class User {
//
//    @Id
//    @GeneratedValue
//    private UUID userId;
//
//    private String name;
//
//    @Column(unique = true, nullable = false)
//    private String email;
//
//
//    @JsonIgnore
//    private String password;
//
//    @Enumerated(EnumType.STRING)
//    private Role role;
//
//
//    private boolean deleted = false;
//
//    private LocalDateTime createdAt;
//    private LocalDateTime updatedAt;
//
//    private LocalDateTime deletedAt;
//
//    @PrePersist
//    void onCreate() {
//        this.createdAt = LocalDateTime.now();
//        this.updatedAt = LocalDateTime.now();
//    }
//
//    @PreUpdate
//    void onUpdate() {
//        this.updatedAt = LocalDateTime.now();
//    }
//}



//
//@Entity
//@Table(name = "users")
//public class User {
//    public enum Role {
//        ADMIN,
//        USER,
//        THEATRE
//    }
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long userId;
//
//    private String name;
//    private String email;
//    private String password;
//
//    @Enumerated(EnumType.STRING)
//    private Role role; // ADMIN, USER, THEATRE


 //   private LocalDateTime createdAt;


