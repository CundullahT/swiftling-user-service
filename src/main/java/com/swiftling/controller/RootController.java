package com.swiftling.controller;

import com.swiftling.dto.UserDTO;
import com.swiftling.dto.wrapper.ExceptionWrapper;
import com.swiftling.dto.wrapper.ResponseWrapper;
import com.swiftling.service.RootService;
import com.swiftling.util.SwaggerExamples;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:8762")
@RequestMapping("/api/v1/root")
public class RootController {

    private final RootService rootService;

    public RootController(RootService rootService) {
        this.rootService = rootService;
    }

    @RolesAllowed("Root")
    @PostMapping("/admin/create")
    @Operation(summary = "Create an admin user.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDTO.class),
                            examples = @ExampleObject(value = SwaggerExamples.USER_CREATE_REQUEST_EXAMPLE))))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User is successfully created.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseWrapper.class),
                            examples = @ExampleObject(value = SwaggerExamples.ADMIN_USER_CREATE_RESPONSE_EXAMPLE))),
            @ApiResponse(responseCode = "409", description = "User already exists.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionWrapper.class),
                            examples = @ExampleObject(value = SwaggerExamples.USER_ALREADY_EXISTS_RESPONSE_EXAMPLE))),
            @ApiResponse(responseCode = "400", description = "Invalid Input(s)",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionWrapper.class),
                            examples = @ExampleObject(value = SwaggerExamples.VALIDATION_EXCEPTION_RESPONSE_EXAMPLE))),
            @ApiResponse(responseCode = "403", description = "Access is denied",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionWrapper.class),
                            examples = @ExampleObject(value = SwaggerExamples.ACCESS_DENIED_FORBIDDEN_RESPONSE_EXAMPLE)))})
    public ResponseEntity<ResponseWrapper> adminCreate(@RequestBody UserDTO userDTO) {

        UserDTO createdAdmin = rootService.createAdmin(userDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(ResponseWrapper.builder()
                .statusCode(HttpStatus.CREATED)
                .success(true)
                .message("User is successfully created.")
                .data(createdAdmin)
                .build());

    }

    @RolesAllowed("Root")
    @PostMapping("/user/create")
    @Operation(summary = "Create a regular user.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDTO.class),
                            examples = @ExampleObject(value = SwaggerExamples.USER_CREATE_REQUEST_EXAMPLE))))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User is successfully created.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseWrapper.class),
                            examples = @ExampleObject(value = SwaggerExamples.REGULAR_USER_CREATE_RESPONSE_EXAMPLE))),
            @ApiResponse(responseCode = "409", description = "User already exists.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionWrapper.class),
                            examples = @ExampleObject(value = SwaggerExamples.USER_ALREADY_EXISTS_RESPONSE_EXAMPLE))),
            @ApiResponse(responseCode = "400", description = "Invalid Input(s)",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionWrapper.class),
                            examples = @ExampleObject(value = SwaggerExamples.VALIDATION_EXCEPTION_RESPONSE_EXAMPLE))),
            @ApiResponse(responseCode = "403", description = "Access is denied",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionWrapper.class),
                            examples = @ExampleObject(value = SwaggerExamples.ACCESS_DENIED_FORBIDDEN_RESPONSE_EXAMPLE)))})
    public ResponseEntity<ResponseWrapper> userCreate(@RequestBody UserDTO userDTO) {

        UserDTO createdUser = rootService.createUser(userDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(ResponseWrapper.builder()
                .statusCode(HttpStatus.CREATED)
                .success(true)
                .message("User is successfully created.")
                .data(createdUser)
                .build());

    }

}
