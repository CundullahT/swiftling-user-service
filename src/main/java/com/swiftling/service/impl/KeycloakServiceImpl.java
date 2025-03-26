package com.swiftling.service.impl;

import com.swiftling.service.KeycloakService;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class KeycloakServiceImpl implements KeycloakService {

    @Override
    public String getAccessToken() {
        KeycloakAuthenticationToken keycloakAuthenticationToken = getAuthentication();
        return "Bearer " + keycloakAuthenticationToken.getAccount()
                .getKeycloakSecurityContext().getTokenString();
    }

    private KeycloakAuthenticationToken getAuthentication() {
        return (KeycloakAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
    }

}
