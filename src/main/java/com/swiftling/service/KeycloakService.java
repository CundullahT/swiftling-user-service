package com.swiftling.service;

import com.swiftling.dto.AccountDTO;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;

public interface KeycloakService {

    String getAccessToken();

    String getLoggedInUserName();

    KeycloakAuthenticationToken getAuthentication();

    void userCreate(AccountDTO accountDTO);

    void enableUser(String username);

    void resetUserPassword(String username, String newPassword);

}
