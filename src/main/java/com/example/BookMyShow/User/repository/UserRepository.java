package com.example.BookMyShow.User.repository;

import com.example.BookMyShow.User.model.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {


    Optional<User>  findByEmail(String email);

    User findByName(String name);


    Optional<User> findByEmailAndDeletedFalse(String email);


    Page<User> findAllByDeletedFalse(Pageable pageable);

    Optional<User> findByNameAndDeletedFalse(String name);

    Optional<User> findByUserIdAndDeletedFalse(UUID userId);

    List<User> findByDeletedTrueAndDeletedAtBefore(LocalDateTime time);


}
