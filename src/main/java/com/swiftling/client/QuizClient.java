package com.swiftling.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@FeignClient(value = "swiftling-quiz-service")
public interface QuizClient {

    @DeleteMapping("/api/v1/phrase/delete-all-user-quizzes")
    ResponseEntity<Void> deleteUserQuizzes(@RequestParam UUID externalOwnerUserAccountId);

}
