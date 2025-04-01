package com.swiftling.dto;

import com.swiftling.enums.Status;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long id;

    @NotBlank(message = "Firstname is a required field.")
    @Size(max = 24, min = 2, message = "Firstname must be between 2 and 24 characters long.")
    private String firstname;

    @NotBlank(message = "Lastname is a required field.")
    @Size(max = 24, min = 2, message = "Lastname must be between 2 and 24 characters long.")
    private String lastname;

    @NotBlank(message = "Username is a required field.")
    @Size(max = 24, min = 6, message = "Username must be between 6 and 24 characters long.")
    private String username;

    @NotBlank(message = "Email is a required field.")
    @Email(message = "Email must be in a valid email format.")
    private String email;

    @NotBlank(message = "Password is a required field.")
    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$", message = "The password must be at " +
            "least 8 characters long and include at least 1 uppercase letter, 1 lowercase letter " +
            "and 1 digit.")
    private String password;

    private Status status;

    private String role;

}
