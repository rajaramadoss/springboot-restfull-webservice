package com.example.project.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(description = "UserDto Model Information")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;
    //User firstName shoud not be null or empty

    @Schema(description = "User First Name")
    @NotEmpty(message = "user first Name should not be null or empty")
    private String firstName;
    //User lastName shoud not be null or empty

    @Schema(description = "User Last Name")
    @NotEmpty(message = "User lastName shoud not be null or empty")
    private String lastName;

    @Schema(description = "User Email Address")
    //User firstName shoud not be null or empty
    @NotEmpty(message = "User firstName shoud not be null or empty")
    @Email(message = "Email address should be valid")
    private String email;

}
