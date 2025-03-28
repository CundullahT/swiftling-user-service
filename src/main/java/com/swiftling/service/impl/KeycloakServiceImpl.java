package com.swiftling.service.impl;

import com.swiftling.service.KeycloakService;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class KeycloakServiceImpl implements KeycloakService {

    @Override
    public String getAccessToken() {
        KeycloakAuthenticationToken keycloakAuthenticationToken = getAuthentication();
        return "Bearer " + keycloakAuthenticationToken.getAccount()
                .getKeycloakSecurityContext().getTokenString();
    }

    @Override
    public String getLoggedInUserName() {
        Authentication authentication = getAuthentication();
        Map<String, Object> attributes = ((JwtAuthenticationToken) authentication).getTokenAttributes();
        return (String) attributes.get("preferred_username");
    }

    @Override
    public KeycloakAuthenticationToken getAuthentication() {
        return (KeycloakAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
    }

}
