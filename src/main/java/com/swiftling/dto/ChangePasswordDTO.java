package com.swiftling.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangePasswordDTO {

    @NotBlank(message = "You need to enter your current password.")
    private String oldPassword;

    @NotBlank(message = "You need to enter your new password.")
    @Pattern(regexp = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}", message = "The password must be at " +
            "least 8 characters long and include at least 1 uppercase letter, 1 lowercase letter " +
            "and 1 digit.")
    private String newPassword;

    @NotBlank(message = "Passwords should match.")
    private String confirmPassword;

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
        checkConfirmPassword();
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        checkConfirmPassword();
    }

    private void checkConfirmPassword() {
        if (newPassword == null || confirmPassword == null) return;
        else if (!newPassword.equals(confirmPassword)) {
            confirmPassword = null;
        }
    }

}
