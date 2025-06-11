package com.swiftling.client;

import com.swiftling.dto.UserIdEmailRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "swiftling-notification-service")
public interface NotificationClient {

    @PostMapping("/api/v1/user-id-email")
    ResponseEntity<UserIdEmailRequestDTO> createUserIdEmail(@RequestBody UserIdEmailRequestDTO userIdEmailRequestDTO);

    @PutMapping("/api/v1/user-id-email")
    ResponseEntity<UserIdEmailRequestDTO> updateUserIdEmail(@RequestBody UserIdEmailRequestDTO userIdEmailRequestDTO);

}
