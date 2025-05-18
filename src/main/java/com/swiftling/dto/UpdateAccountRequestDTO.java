package com.swiftling.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateAccountRequestDTO {

    @NotBlank(message = "First name is a required field.")
    @Size(max = 24, min = 2, message = "The first name must be between 2 and 24 characters long.")
    private String firstName;

    @NotBlank(message = "Last name is a required field.")
    @Size(max = 24, min = 2, message = "The last name must be between 2 and 24 characters long.")
    private String lastName;

    @NotBlank(message = "Email is a required field.")
    @Email(message = "The email address must be in a valid email format.")
    private String email;

}
