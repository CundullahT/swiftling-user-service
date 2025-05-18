package com.swiftling.controller;

import com.swiftling.dto.AccountDTO;
import com.swiftling.dto.ChangePasswordRequestDTO;
import com.swiftling.dto.ResetPasswordRequestDTO;
import com.swiftling.dto.wrapper.ExceptionWrapper;
import com.swiftling.dto.wrapper.ResponseWrapper;
import com.swiftling.service.AccountService;
import com.swiftling.util.SwaggerExamples;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:8762")
@RequestMapping("/api/v1/account")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/signup")
    @Operation(summary = "Sign up a new user account.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = AccountDTO.class),
                            examples = @ExampleObject(value = SwaggerExamples.USER_CREATE_REQUEST_EXAMPLE))))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "The user account has been signed up successfully.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseWrapper.class),
                            examples = @ExampleObject(value = SwaggerExamples.USER_CREATE_RESPONSE_EXAMPLE))),
            @ApiResponse(responseCode = "409", description = "The user account with the given email address (sample@email.com) already exists.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionWrapper.class),
                            examples = @ExampleObject(value = SwaggerExamples.USER_ALREADY_EXISTS_RESPONSE_EXAMPLE))),
            @ApiResponse(responseCode = "400", description = "Invalid Input(s)",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionWrapper.class),
                            examples = @ExampleObject(value = SwaggerExamples.VALIDATION_EXCEPTION_RESPONSE_EXAMPLE))),
            @ApiResponse(responseCode = "403", description = "Access is denied",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionWrapper.class),
                            examples = @ExampleObject(value = SwaggerExamples.ACCESS_DENIED_FORBIDDEN_RESPONSE_EXAMPLE)))})
    public ResponseEntity<ResponseWrapper> signUp(@Valid @RequestBody AccountDTO accountDTO) {

        AccountDTO createdAccount = accountService.signUp(accountDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(ResponseWrapper.builder()
                .statusCode(HttpStatus.CREATED)
                .success(true)
                .message("The user account has been signed up successfully.")
                .data(createdAccount)
                .build());

    }

    @GetMapping("/enable")
    @Operation(summary = "Enable/verify a new user account.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The user account has been enabled successfully.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseWrapper.class),
                            examples = @ExampleObject(value = SwaggerExamples.USER_ENABLE_RESPONSE_EXAMPLE))),
            @ApiResponse(responseCode = "404", description = "The verification token does not exist.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionWrapper.class),
                            examples = @ExampleObject(value = SwaggerExamples.TOKEN_NOT_FOUND_RESPONSE_EXAMPLE))),
            @ApiResponse(responseCode = "404", description = "The user account does not exist: + sample@email.com",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionWrapper.class),
                            examples = @ExampleObject(value = SwaggerExamples.USER_NOT_FOUND_RESPONSE_EXAMPLE))),
            @ApiResponse(responseCode = "410", description = "The verification token has expired.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionWrapper.class),
                            examples = @ExampleObject(value = SwaggerExamples.TOKEN_EXPIRED_RESPONSE_EXAMPLE))),
            @ApiResponse(responseCode = "410", description = "The verification token has expired.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionWrapper.class),
                            examples = @ExampleObject(value = SwaggerExamples.USER_ALREADY_ENABLED_RESPONSE_EXAMPLE))),
            @ApiResponse(responseCode = "403", description = "Access is denied",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionWrapper.class),
                            examples = @ExampleObject(value = SwaggerExamples.ACCESS_DENIED_FORBIDDEN_RESPONSE_EXAMPLE)))})
    public ResponseEntity<ResponseWrapper> enable(@RequestParam(value = "token", required = true) String token) {

        accountService.enable(token);

        return ResponseEntity.status(HttpStatus.CREATED).body(ResponseWrapper.builder()
                .statusCode(HttpStatus.CREATED)
                .success(true)
                .message("The user account has been enabled successfully.")
                .build());

    }

    @GetMapping("/forgot-pass")
    @Operation(summary = "Request a forgot password email.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The forgot password email was sent successfully.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseWrapper.class),
                            examples = @ExampleObject(value = SwaggerExamples.USER_FORGOT_PASSWORD_RESPONSE_EXAMPLE))),
            @ApiResponse(responseCode = "404", description = "The user account does not exist.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionWrapper.class),
                            examples = @ExampleObject(value = SwaggerExamples.USER_NOT_FOUND_RESPONSE_EXAMPLE))),
            @ApiResponse(responseCode = "400", description = "Invalid Input(s)",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionWrapper.class),
                            examples = @ExampleObject(value = SwaggerExamples.VALIDATION_EXCEPTION_RESPONSE_EXAMPLE))),
            @ApiResponse(responseCode = "403", description = "Access is denied",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionWrapper.class),
                            examples = @ExampleObject(value = SwaggerExamples.ACCESS_DENIED_FORBIDDEN_RESPONSE_EXAMPLE)))})
    public ResponseEntity<ResponseWrapper> forgotPassword(@RequestParam("email") String email) {

        accountService.forgotPassword(email);

        return ResponseEntity.ok(ResponseWrapper.builder()
                .statusCode(HttpStatus.OK)
                .success(true)
                .message("The forgot password email was sent successfully.")
                .build());

    }

    @PostMapping("/reset-pass")
    @Operation(summary = "Reset password using a valid reset token.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResetPasswordRequestDTO.class),
                            examples = @ExampleObject(value = SwaggerExamples.RESET_PASSWORD_REQUEST_EXAMPLE))))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Password has been reset successfully.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseWrapper.class),
                            examples = @ExampleObject(value = SwaggerExamples.RESET_PASSWORD_RESPONSE_EXAMPLE))),
            @ApiResponse(responseCode = "404", description = "The token does not exist.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionWrapper.class),
                            examples = @ExampleObject(value = SwaggerExamples.TOKEN_NOT_FOUND_RESPONSE_EXAMPLE))),
            @ApiResponse(responseCode = "404", description = "The user account does not exist: + sample@email.com",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionWrapper.class),
                            examples = @ExampleObject(value = SwaggerExamples.USER_NOT_FOUND_RESPONSE_EXAMPLE))),
            @ApiResponse(responseCode = "410", description = "The token has expired.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionWrapper.class),
                            examples = @ExampleObject(value = SwaggerExamples.TOKEN_EXPIRED_RESPONSE_EXAMPLE))),
            @ApiResponse(responseCode = "403", description = "Access is denied",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionWrapper.class),
                            examples = @ExampleObject(value = SwaggerExamples.ACCESS_DENIED_FORBIDDEN_RESPONSE_EXAMPLE)))})
    public ResponseEntity<ResponseWrapper> resetPassword(@RequestParam(value = "token", required = true) String token, @Valid @RequestBody ResetPasswordRequestDTO requestDTO) {

        accountService.resetPassword(token, requestDTO.getNewPassword());

        return ResponseEntity.ok(ResponseWrapper.builder()
                .statusCode(HttpStatus.OK)
                .success(true)
                .message("Password has been reset successfully.")
                .build());
    }

    @PostMapping("/change-pass")
    @Operation(summary = "Change password.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResetPasswordRequestDTO.class),
                            examples = @ExampleObject(value = SwaggerExamples.CHANGE_PASSWORD_REQUEST_EXAMPLE))))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Password has been changed successfully.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseWrapper.class),
                            examples = @ExampleObject(value = SwaggerExamples.CHANGE_PASSWORD_RESPONSE_EXAMPLE))),
            @ApiResponse(responseCode = "404", description = "The user account does not exist: + sample@email.com",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionWrapper.class),
                            examples = @ExampleObject(value = SwaggerExamples.USER_NOT_FOUND_RESPONSE_EXAMPLE))),
            @ApiResponse(responseCode = "403", description = "Access is denied",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionWrapper.class),
                            examples = @ExampleObject(value = SwaggerExamples.ACCESS_DENIED_FORBIDDEN_RESPONSE_EXAMPLE)))})
    public ResponseEntity<ResponseWrapper> changePassword(@Valid @RequestBody ChangePasswordRequestDTO requestDTO) {

        accountService.changePassword(requestDTO.getCurrentPassword(), requestDTO.getNewPassword());

        return ResponseEntity.ok(ResponseWrapper.builder()
                .statusCode(HttpStatus.OK)
                .success(true)
                .message("Password has been changed successfully.")
                .build());
    }

}
