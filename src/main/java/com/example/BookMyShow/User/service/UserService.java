package com.example.BookMyShow.User.service;

import com.example.BookMyShow.User.dto.userrequestdto.LoginRequestDto;
import com.example.BookMyShow.User.dto.userrequestdto.UserRequestDto;
import com.example.BookMyShow.User.dto.userresponsedto.UserResponseDto;
import com.example.BookMyShow.generic.CommonResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserService {
    String addUser( UserRequestDto userRequestDto);

    String addAdmin( UserRequestDto userRequestDto);

    String addTheatre( UserRequestDto userRequestDto);

    String login(LoginRequestDto UserRequestDto);

    List<UserResponseDto> getAllUsers();

    String deleteUserbyId(UUID id);

    String updateUserbyId(UUID id, UserRequestDto userRequestDto);

    UserResponseDto getAllUsersbyName(String name);
    String deleteUserbyName(String username);

    List<UserResponseDto> getAllAdmin();

    UserResponseDto getAllUserbyId(UUID id);

    List<UserResponseDto> getAllThetare();
}
