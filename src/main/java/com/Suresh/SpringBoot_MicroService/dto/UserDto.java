package com.Suresh.SpringBoot_MicroService.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long id;

    @NotEmpty(message = "User first name should not be null or empty")
    private String firstname;

    @NotEmpty(message = "User first name should not be null or empty")
    private String lastname;

    @NotEmpty(message = "User Email should not be null or empty")
    @Email(message = "Email Address should be valid")
    private String email;
}
