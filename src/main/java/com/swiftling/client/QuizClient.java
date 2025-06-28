package com.swiftling.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@FeignClient(value = "swiftling-quiz-service")
public interface QuizClient {

    @DeleteMapping("/api/v1/quiz/delete-all-user-quizzes")
    ResponseEntity<Void> deleteUserQuizzes(@RequestHeader("Authorization") String authToken, @RequestParam(value = "external-user-id", required = true) UUID externalOwnerUserAccountId);

}
