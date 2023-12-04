package com.example.project.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;
    //User firstName shoud not be null or empty

    @NotEmpty(message = "user first Name should not be null or empty")
    private String firstName;
    //User lastName shoud not be null or empty

    @NotEmpty(message = "User lastName shoud not be null or empty")
    private String lastName;

    //User firstName shoud not be null or empty
    @NotEmpty(message = "User firstName shoud not be null or empty")
    @Email(message = "Email address should be valid")
    private String email;

}
