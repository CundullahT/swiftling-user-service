package com.swiftling.controller;

import com.swiftling.dto.UserDTO;
import com.swiftling.dto.wrapper.ExceptionWrapper;
import com.swiftling.dto.wrapper.ResponseWrapper;
import com.swiftling.service.AdminService;
import com.swiftling.util.SwaggerExamples;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:8762")
@RequestMapping("/api/v1/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @RolesAllowed("Admin")
    @PostMapping("/user/create")
    @Operation(summary = "Create a regular user.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDTO.class),
                            examples = @ExampleObject(value = SwaggerExamples.USER_CREATE_REQUEST_EXAMPLE))))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "The regular user is successfully created.",
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
    public ResponseEntity<ResponseWrapper> createUser(@Valid @RequestBody UserDTO userDTO) {

        UserDTO createdUser = adminService.createUser(userDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(ResponseWrapper.builder()
                .statusCode(HttpStatus.CREATED)
                .success(true)
                .message("The regular user is successfully created.")
                .data(createdUser)
                .build());

    }

    @RolesAllowed("Admin")
    @GetMapping("/user/read/{username}")
    @Operation(summary = "Read a regular user by username.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The regular user is successfully retrieved.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseWrapper.class),
                            examples = @ExampleObject(value = SwaggerExamples.REGULAR_USER_GET_RESPONSE_SINGLE_EXAMPLE))),
            @ApiResponse(responseCode = "404", description = "User does not exist.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionWrapper.class),
                            examples = @ExampleObject(value = SwaggerExamples.USER_NOT_FOUND_RESPONSE_EXAMPLE))),
            @ApiResponse(responseCode = "403", description = "Access is denied",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionWrapper.class),
                            examples = @ExampleObject(value = SwaggerExamples.ACCESS_DENIED_FORBIDDEN_RESPONSE_EXAMPLE)))})
    public ResponseEntity<ResponseWrapper> getUserByUsername(@PathVariable("username") String username) {

        UserDTO foundUser = adminService.readUserByUsername(username);

        return ResponseEntity
                .ok(ResponseWrapper.builder()
                        .statusCode(HttpStatus.OK)
                        .success(true)
                        .message("The regular user is successfully retrieved.")
                        .data(foundUser)
                        .build());

    }

    @RolesAllowed("Admin")
    @GetMapping("/user/read/all")
    @Operation(summary = "Read all regular users.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All the regular users are successfully retrieved.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseWrapper.class),
                            examples = @ExampleObject(value = SwaggerExamples.REGULAR_USER_GET_RESPONSE_LIST_EXAMPLE))),
            @ApiResponse(responseCode = "403", description = "Access is denied",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionWrapper.class),
                            examples = @ExampleObject(value = SwaggerExamples.ACCESS_DENIED_FORBIDDEN_RESPONSE_EXAMPLE)))})
    public ResponseEntity<ResponseWrapper> getAllUsers() {

        List<UserDTO> foundUsers = adminService.readAllUsers();

        return ResponseEntity
                .ok(ResponseWrapper.builder()
                        .statusCode(HttpStatus.OK)
                        .success(true)
                        .message("All the regular users are successfully retrieved.")
                        .data(foundUsers)
                        .build());

    }

    @RolesAllowed("Admin")
    @PutMapping("/user/update/{username}")
    @Operation(summary = "Update a regular user by username.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDTO.class),
                            examples = @ExampleObject(value = SwaggerExamples.USER_UPDATE_REQUEST_EXAMPLE))))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The regular user is successfully updated.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseWrapper.class),
                            examples = @ExampleObject(value = SwaggerExamples.REGULAR_USER_UPDATE_RESPONSE_EXAMPLE))),
            @ApiResponse(responseCode = "404", description = "User does not exist.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionWrapper.class),
                            examples = @ExampleObject(value = SwaggerExamples.USER_NOT_FOUND_RESPONSE_EXAMPLE))),
            @ApiResponse(responseCode = "400", description = "Invalid Input(s)",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionWrapper.class),
                            examples = @ExampleObject(value = SwaggerExamples.VALIDATION_EXCEPTION_RESPONSE_EXAMPLE))),
            @ApiResponse(responseCode = "403", description = "Access is denied",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionWrapper.class),
                            examples = @ExampleObject(value = SwaggerExamples.ACCESS_DENIED_FORBIDDEN_RESPONSE_EXAMPLE)))})
    public ResponseEntity<ResponseWrapper> updateUser(@PathVariable("username") String username, @Valid @RequestBody UserDTO userDTO) {

        UserDTO updatedUser = adminService.updateUser(username, userDTO);

        return ResponseEntity
                .ok(ResponseWrapper.builder()
                        .statusCode(HttpStatus.OK)
                        .success(true)
                        .message("The regular user is successfully updated.")
                        .data(updatedUser)
                        .build());

    }

    @RolesAllowed("Admin")
    @PatchMapping("/user/enable/{username}")
    @Operation(summary = "Enable a regular user by username.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The regular user is successfully enabled.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseWrapper.class),
                            examples = @ExampleObject(value = SwaggerExamples.REGULAR_USER_ENABLE_RESPONSE_EXAMPLE))),
            @ApiResponse(responseCode = "404", description = "User does not exist.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionWrapper.class),
                            examples = @ExampleObject(value = SwaggerExamples.USER_NOT_FOUND_RESPONSE_EXAMPLE))),
            @ApiResponse(responseCode = "403", description = "Access is denied",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionWrapper.class),
                            examples = @ExampleObject(value = SwaggerExamples.ACCESS_DENIED_FORBIDDEN_RESPONSE_EXAMPLE)))})
    public ResponseEntity<ResponseWrapper> enableUser(@PathVariable("username") String username) {

        adminService.enableUser(username);

        return ResponseEntity
                .ok(ResponseWrapper.builder()
                        .statusCode(HttpStatus.OK)
                        .success(true)
                        .message("The regular user is successfully enabled.")
                        .build());

    }

    @RolesAllowed("Admin")
    @PatchMapping("/user/disable/{username}")
    @Operation(summary = "Disable a regular user by username.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The regular user is successfully disabled.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseWrapper.class),
                            examples = @ExampleObject(value = SwaggerExamples.REGULAR_USER_DISABLE_RESPONSE_EXAMPLE))),
            @ApiResponse(responseCode = "404", description = "User does not exist.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionWrapper.class),
                            examples = @ExampleObject(value = SwaggerExamples.USER_NOT_FOUND_RESPONSE_EXAMPLE))),
            @ApiResponse(responseCode = "403", description = "Access is denied",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionWrapper.class),
                            examples = @ExampleObject(value = SwaggerExamples.ACCESS_DENIED_FORBIDDEN_RESPONSE_EXAMPLE)))})
    public ResponseEntity<ResponseWrapper> disableUser(@PathVariable("username") String username) {

        adminService.disableUser(username);

        return ResponseEntity
                .ok(ResponseWrapper.builder()
                        .statusCode(HttpStatus.OK)
                        .success(true)
                        .message("The regular user is successfully disabled.")
                        .build());

    }

    @RolesAllowed("Admin")
    @DeleteMapping("/user/delete/{username}")
    @Operation(summary = "Delete a regular user by username.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The regular user is successfully deleted.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseWrapper.class),
                            examples = @ExampleObject(value = SwaggerExamples.REGULAR_USER_DELETE_RESPONSE_EXAMPLE))),
            @ApiResponse(responseCode = "404", description = "User does not exist.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionWrapper.class),
                            examples = @ExampleObject(value = SwaggerExamples.USER_NOT_FOUND_RESPONSE_EXAMPLE))),
            @ApiResponse(responseCode = "409", description = "User can not be deleted.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionWrapper.class),
                            examples = @ExampleObject(value = SwaggerExamples.USER_NOT_DELETED_RESPONSE_EXAMPLE))),
            @ApiResponse(responseCode = "403", description = "Access is denied",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionWrapper.class),
                            examples = @ExampleObject(value = SwaggerExamples.ACCESS_DENIED_FORBIDDEN_RESPONSE_EXAMPLE)))})
    public ResponseEntity<ResponseWrapper> deleteUser(@PathVariable("username") String username) {

        adminService.deleteUser(username);

        return ResponseEntity
                .ok(ResponseWrapper.builder()
                        .statusCode(HttpStatus.OK)
                        .success(true)
                        .message("The regular user is successfully deleted.")
                        .build());

    }

}
