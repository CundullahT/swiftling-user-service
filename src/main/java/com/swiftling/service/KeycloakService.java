package com.swiftling.service;

import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;

public interface KeycloakService {

    String getAccessToken();

    String getLoggedInUserName();

    KeycloakAuthenticationToken getAuthentication();

}
