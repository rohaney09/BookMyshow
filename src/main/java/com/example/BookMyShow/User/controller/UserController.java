package com.example.BookMyShow.User.controller;


import com.example.BookMyShow.User.dto.userrequestdto.LoginRequestDto;
import com.example.BookMyShow.User.dto.userrequestdto.UserRequestDto;
import com.example.BookMyShow.User.service.UserService;
import com.example.BookMyShow.generic.CommonResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@Validated
@AllArgsConstructor
@RequestMapping("/users")
@RestController
@SecurityRequirement(name = "bearerAuth")
public class UserController {

   private final UserService userService;



    @PostMapping("/registerUser")
    public ResponseEntity<CommonResponse> addUser(@Valid @RequestBody UserRequestDto userRequestDto){
       // return ResponseEntity.ok(new CommonResponse(false,"added user Successfully", userService.addUser(userRequestDto)));
  return ResponseEntity.ok(CommonResponse.success(  "User added ",userService.addUser(userRequestDto)));

    }

    @PostMapping("/registerAsAdmin")
    public ResponseEntity<CommonResponse> addAdmin(@Valid @RequestBody UserRequestDto userRequestDto){
        return ResponseEntity.ok(new CommonResponse(false,"added admin Successfully", userService.addAdmin(userRequestDto)));
    }

    @PostMapping("/registerAsTheatre")
    public ResponseEntity<CommonResponse> addTheatre(@Valid @RequestBody UserRequestDto userRequestDto){
        return ResponseEntity.ok(new CommonResponse(false,"theatre admin Successfully added", userService.addTheatre(userRequestDto)));
    }


   /* @PostMapping("/login")
    public ResponseEntity<CommonResponse> login(@Valid @RequestBody LoginRequestDto loginRequestDto){
        return ResponseEntity.ok(new CommonResponse(false,"Login Successfully", userService.login(loginRequestDto)));

    }*/

    @PostMapping("/login")
    public ResponseEntity<CommonResponse> login(@Valid @RequestBody LoginRequestDto dto) {

        String token = userService.login(dto);

        return ResponseEntity.ok(
                CommonResponse.success("Login successful", token)
        );
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/getAllUsers")
    public ResponseEntity<CommonResponse> getAllUsers(){
        return ResponseEntity.ok(new CommonResponse(false,"fetched all users Successfully",userService.getAllUsers()));

    }

    @GetMapping("/getAllAdmin")
    public ResponseEntity<CommonResponse> getAllAdmin(){
        return ResponseEntity.ok(new CommonResponse(false,"fetched all admins Successfully",userService.getAllAdmin()));

    }

    @GetMapping("/getAllThetare")
    public ResponseEntity<CommonResponse> getAllThetare(){
        return ResponseEntity.ok(new CommonResponse(false,"fetched all theatre admins Successfully",userService.getAllThetare()));

    }

    @GetMapping("/getAllUsersbyId/{id}")
    public ResponseEntity<CommonResponse> getAllUsersbyId(@PathVariable UUID id){
        return ResponseEntity.ok(new CommonResponse(false,"fetched user by id Successfully",userService.getAllUserbyId(id)));
    }

    @GetMapping("/getAllUsersbyName/{name}")
    public ResponseEntity<CommonResponse> getAllUsersbyName(@PathVariable String name){
        return ResponseEntity.ok(new CommonResponse(false,"fetched user by name Successfully",userService.getAllUsersbyName(name)));
    }


    @DeleteMapping("/deleteUserbyId/{id}")
    public ResponseEntity<CommonResponse> deleteUserbyId(@PathVariable UUID id){
        return ResponseEntity.ok(new CommonResponse(false,"deleted  user Successfully", userService.deleteUserbyId(id)));

    }

    @DeleteMapping("/deleteUserbyName/{name}")
    public ResponseEntity<CommonResponse> deleteUserbyName(@PathVariable String name){
        return ResponseEntity.ok(new CommonResponse(false,"deleted  user Successfully", userService.deleteUserbyName(name)));

    }

    @PostMapping("/updateUserbyId/{id}")
    public ResponseEntity<CommonResponse> updateUserbyId(@PathVariable UUID id,@Valid @RequestBody UserRequestDto userRequestDto){
        return ResponseEntity.ok(new CommonResponse(false,"fetched all users Successfully",   userService.updateUserbyId(id,userRequestDto)));

    }


}
