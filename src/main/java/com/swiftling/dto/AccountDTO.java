package com.swiftling.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountDTO {

    @JsonIgnore
    private Long id;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID externalId;

    @NotBlank(message = "First name is a required field.")
    @Size(max = 24, min = 2, message = "The first name must be between 2 and 24 characters long.")
    private String firstName;

    @NotBlank(message = "Last name is a required field.")
    @Size(max = 24, min = 2, message = "The last name must be between 2 and 24 characters long.")
    private String lastName;

    @NotBlank(message = "Email is a required field.")
    @Email(message = "The email address must be in a valid email format.")
    private String email;

    @NotBlank(message = "Password is a required field.")
    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$", message = "The password must be at " +
            "least 8 characters long and include at least 1 uppercase letter, 1 lowercase letter, 1 digit " +
            "and 1 special character.")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

}
