package com.swiftling.client;

import com.swiftling.dto.UserIdEmailRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@FeignClient(value = "swiftling-notification-service")
public interface NotificationClient {

    @PostMapping("/api/v1/user-id-email")
    ResponseEntity<UserIdEmailRequestDTO> createUserIdEmail(@RequestBody UserIdEmailRequestDTO userIdEmailRequestDTO);

    @PutMapping("/api/v1/user-id-email")
    ResponseEntity<UserIdEmailRequestDTO> updateUserIdEmail(@RequestBody UserIdEmailRequestDTO userIdEmailRequestDTO);

    @DeleteMapping("/api/v1/user-id-email")
    ResponseEntity<UserIdEmailRequestDTO> deleteUserIdEmail(@RequestParam(value = "externalId", required = true) UUID externalId);

}
