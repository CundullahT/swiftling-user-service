package com.swiftling.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@FeignClient(value = "swiftling-phrase-service")
public interface PhraseClient {

    @DeleteMapping("/api/v1/phrase/delete-all-user-phrases")
    ResponseEntity<Void> deleteUserPhrases(@RequestHeader("Authorization") String authToken, @RequestParam(value = "external-user-id", required = true) UUID externalOwnerUserAccountId);

}
