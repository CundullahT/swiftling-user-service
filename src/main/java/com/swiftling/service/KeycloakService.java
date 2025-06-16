package com.swiftling.service;

import com.swiftling.dto.AccountDTO;
import com.swiftling.dto.UpdateAccountRequestDTO;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

public interface KeycloakService {

    String getAccessToken();

    String getLoggedInUserName();

    JwtAuthenticationToken getAuthentication();

    void userCreate(AccountDTO accountDTO);

    void userUpdate(UpdateAccountRequestDTO requestDTO);

    void enableUser(String username);

    void resetUserPassword(String username, String newPassword);

    void delete(String username);

    String getLogoutRedirectUrl(String frontendUrl);

}
