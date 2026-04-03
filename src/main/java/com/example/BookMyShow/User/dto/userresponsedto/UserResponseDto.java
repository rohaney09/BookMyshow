package com.example.BookMyShow.User.dto.userresponsedto;



import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;



import java.util.UUID;

@Getter
@Setter
public class UserResponseDto {

    private UUID userId;
    private String name;
    private String email;
}
