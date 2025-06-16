package com.swiftling.service;

import com.swiftling.dto.AccountDTO;
import com.swiftling.dto.UpdateAccountRequestDTO;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;

public interface KeycloakService {

    String getAccessToken();

    String getLoggedInUserName();

    KeycloakAuthenticationToken getAuthentication();

    void userCreate(AccountDTO accountDTO);

    void userUpdate(String loggedInEmailUsername, UpdateAccountRequestDTO requestDTO);

    void enableUser(String username);

    void resetUserPassword(String username, String newPassword);

    void delete(String username);

    String getLogoutRedirectUrl(String frontendUrl);

}
