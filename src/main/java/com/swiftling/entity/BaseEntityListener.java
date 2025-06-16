package com.swiftling.entity;

import com.swiftling.exception.AnonymousPersistenceNotAllowedException;
import com.swiftling.service.KeycloakService;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class BaseEntityListener {

    private final KeycloakService keycloakService;

    public BaseEntityListener(KeycloakService keycloakService) {
        this.keycloakService = keycloakService;
    }

    @PrePersist
    private void onPrePersist(BaseEntity baseEntity) {

        KeycloakAuthenticationToken authentication = keycloakService.getAuthentication();

        baseEntity.setInsertDateTime(LocalDateTime.now());
        baseEntity.setLastUpdateDateTime(LocalDateTime.now());

        if (authentication != null && !authentication.getName().equals("anonymousUser")) {

            Object principal = authentication.getPrincipal();

            baseEntity.setInsertUserId(((UserPrincipal) principal).getId());
            baseEntity.setLastUpdateUserId(((UserPrincipal) principal).getId());

        } else {
            if (!((baseEntity instanceof Account) || (baseEntity instanceof Token))) {
                throw new AnonymousPersistenceNotAllowedException("Anonymous users are only allowed to create user accounts.");
            }
            baseEntity.setInsertUserId(0L);
            baseEntity.setLastUpdateUserId(0L);
        }

    }

    @PreUpdate
    private void onPreUpdate(BaseEntity baseEntity) {

        KeycloakAuthenticationToken authentication = keycloakService.getAuthentication();

        baseEntity.setLastUpdateDateTime(LocalDateTime.now());

        if (authentication != null && !authentication.getName().equals("anonymousUser")) {

            Object principal = authentication.getPrincipal();

            baseEntity.setLastUpdateUserId(((UserPrincipal) principal).getId());

        }
    }

}
