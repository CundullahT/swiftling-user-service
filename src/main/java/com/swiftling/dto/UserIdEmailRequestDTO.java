package com.swiftling.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserIdEmailRequestDTO {

    @NotNull(message = "External ID is a required field.")
    private UUID externalId;

    @NotBlank(message = "Email is a required field.")
    @Email(message = "The email address must be in a valid email format.")
    private String email;
    
}
