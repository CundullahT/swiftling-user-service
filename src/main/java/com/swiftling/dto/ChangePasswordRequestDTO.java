package com.swiftling.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangePasswordRequestDTO {

    @NotBlank(message = "Current Password is a required field.")
    private String currentPassword;

    @NotBlank(message = "New Password is a required field.")
    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$", message = "The password must be at " +
            "least 8 characters long and include at least 1 uppercase letter, 1 lowercase letter, 1 digit " +
            "and 1 special character.")
    private String newPassword;

}
