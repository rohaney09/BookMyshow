package com.example.BookMyShow.User.service.serviceimp;

import com.example.BookMyShow.User.dto.userrequestdto.LoginRequestDto;
import com.example.BookMyShow.User.dto.userrequestdto.UserRequestDto;
import com.example.BookMyShow.User.dto.userresponsedto.UserResponseDto;
import com.example.BookMyShow.User.model.Role;
import com.example.BookMyShow.User.model.User;
import com.example.BookMyShow.User.repository.UserRepository;
import com.example.BookMyShow.User.service.UserService;
import com.example.BookMyShow.generic.CommonResponse;
import com.example.BookMyShow.generic.exception.DataNotFound;
import com.example.BookMyShow.security.jwt.JwtUtil;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.Authenticator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Slf4j
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final ModelMapper modelMapper;
    private UserRepository userRepository;

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    private final PasswordEncoder passwordEncoder;
    @Transactional
    @Override
    public String addUser(UserRequestDto userRequestDto) {
        Optional<User> existingUser =
                userRepository.findByEmailAndDeletedFalse(userRequestDto.getEmail());


        if (existingUser.isPresent()) {
            log.warn("User with email {} already exists", userRequestDto.getEmail());
            return "User already exists";
        }
        User user = new User();
        user.setName(userRequestDto.getName());
        user.setEmail(userRequestDto.getEmail());
        // encode password
        user.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
        user.setRole(Role.USER);
        userRepository.save(user);
        return "User registered successfully";
    }

    @Transactional
    @Override
    public String addAdmin(UserRequestDto userRequestDto) {
        Optional<User> existingUser =
                userRepository.findByEmailAndDeletedFalse(userRequestDto.getEmail());
        if (existingUser.isPresent()) {
            log.warn("User with email {} already exists", userRequestDto.getEmail());
            return "User already exists";
        }
        User user = new User();
        user.setName(userRequestDto.getName());
        user.setEmail(userRequestDto.getEmail());
        user.setPassword(userRequestDto.getPassword());
        user.setRole(Role.ADMIN);
        userRepository.save(user);
        return "User registered successfully";
    }

    @Override
    public String addTheatre(UserRequestDto userRequestDto) {

        Optional<User> existingUser =
                userRepository.findByEmailAndDeletedFalse(userRequestDto.getEmail());

        if (existingUser.isPresent()) {
            log.warn("User with email {} already exists", userRequestDto.getEmail());
            return "User already exists";
        }

        User user = new User();
        user.setName(userRequestDto.getName());
        user.setEmail(userRequestDto.getEmail());
        user.setPassword(userRequestDto.getPassword());

        user.setRole(Role.THEATRE);

        userRepository.save(user);

        return "User registered successfully";

    }

    @Override
    public UserResponseDto getAllUsersbyName(String name) {
        User user = userRepository.findByName(name);
        UserResponseDto userResponseDto=new UserResponseDto();
        BeanUtils.copyProperties(user,userResponseDto);
        return userResponseDto;
    }


    /*@Override
    public boolean login(LoginRequestDto userRequestDto) {
        log.info("Login attempt for email: {}", userRequestDto.getEmail());
        User loggeduser = userRepository.findByEmail(userRequestDto.getEmail()).orElseThrow(()->new DataNotFound("No user found with given email"));
        if (!loggeduser.getPassword().equals(userRequestDto.getPassword())){
            throw new DataNotFound("Incorrect password for email: " + userRequestDto.getEmail());
        }
        log.info("User {} logged in successfully", userRequestDto.getEmail());
        return true;
    }*/

    /*@PostMapping("/login")
    public ResponseEntity<CommonResponse> login(@Valid @RequestBody LoginRequestDto dto) {

        String token = userService.login(dto);

        return ResponseEntity.ok(
                CommonResponse.success("Login successful", token)
        );
    }*/


    public String login(LoginRequestDto loginRequestDto) {

        // authenticate credentials
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequestDto.getEmail(),
                        loginRequestDto.getPassword()
                )
        );
        // get user
        User user = userRepository.findByEmail(loginRequestDto.getEmail())
                .orElseThrow(() -> new DataNotFound("User not found"));

        // generate token
        return jwtUtil.generateToken(user.getEmail(), user.getRole());
    }




    @Override
    public List<UserResponseDto> getAllUsers() {
        List<User> allUsers = userRepository.findAll();
        List<UserResponseDto> allUserRespnseDto=new ArrayList<>();
        for(User user :allUsers){
            if (user.getRole() == Role.USER) {
                UserResponseDto userResponseDto = new UserResponseDto();
                BeanUtils.copyProperties(user, userResponseDto);
                allUserRespnseDto.add(userResponseDto);
            }
        }
        return  allUserRespnseDto;
    }






    @Cacheable(value = "users", key = "#id")
    @Override
    public UserResponseDto getAllUserbyId(UUID id) {
        User user = userRepository.findById(id).orElseThrow(() -> new DataNotFound("user by id not found"));
        UserResponseDto userResponseDto=new UserResponseDto();
        BeanUtils.copyProperties(user,userResponseDto);
        return userResponseDto;
    }

    @Override
    public List<UserResponseDto> getAllThetare() {
        List<User> allUsers = userRepository.findAll();
        List<UserResponseDto> allUserRespnseDto=new ArrayList<>();
        for(User user :allUsers){
            if (user.getRole() == Role.THEATRE) {
                UserResponseDto userResponseDto = new UserResponseDto();
                BeanUtils.copyProperties(user, userResponseDto);
                allUserRespnseDto.add(userResponseDto);
            }
        }
        return  allUserRespnseDto;
    }



@Transactional
@Override
public String deleteUserbyId(UUID id) {

    User user = userRepository.findByUserIdAndDeletedFalse(id)
            .orElseThrow(() -> new DataNotFound("User not found"));

    //  soft delete
    user.setDeleted(true);
    userRepository.save(user);

    log.info("User with id {} soft deleted", id);

    return "User deleted successfully";
}

    @Scheduled(cron = "0 0 2 * * ?")
    @Transactional
    public void deleteUsersAfterOneYear() {

        LocalDateTime oneYearAgo = LocalDateTime.now().minusYears(1);

        List<User> usersToDelete =
                userRepository.findByDeletedTrueAndDeletedAtBefore(oneYearAgo);

        if (!usersToDelete.isEmpty()) {
            userRepository.deleteAll(usersToDelete);
            log.info("Deleted {} users permanently", usersToDelete.size());
        }
    }

    @Override
    public String updateUserbyId(UUID id, UserRequestDto userRequestDto) {
        User user = userRepository.findById(id).orElseThrow(() -> new DataNotFound("no error"));
        user.setPassword(userRequestDto.getPassword());
        user.setName(userRequestDto.getName());
        user.setEmail(userRequestDto.getEmail());
        userRepository.save(user);
        return "";
    }

    @Transactional
    @Override
    public String deleteUserbyName(String username) {

        User user = userRepository.findByNameAndDeletedFalse(username)
                .orElseThrow(() -> new DataNotFound("User not found"));

        // soft delete
        user.setDeleted(true);
        userRepository.save(user);
        log.info("User with name {} soft deleted", username);
        return "User deleted successfully";
    }

    @Override
    public List<UserResponseDto> getAllAdmin() {
        List<User> allUsers = userRepository.findAll();
        List<UserResponseDto> allUserRespnseDto=new ArrayList<>();
        for(User user :allUsers){
            if (user.getRole() == Role.ADMIN) {
                UserResponseDto userResponseDto = new UserResponseDto();
                BeanUtils.copyProperties(user, userResponseDto);
                allUserRespnseDto.add(userResponseDto);
            }
        }
        return  allUserRespnseDto;
    }
}
