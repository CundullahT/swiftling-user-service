package com.swiftling.service.impl;

import com.swiftling.config.KeycloakProperties;
import com.swiftling.dto.AccountDTO;
import com.swiftling.exception.UserNotFoundException;
import com.swiftling.service.KeycloakService;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class KeycloakServiceImpl implements KeycloakService {

    private final KeycloakProperties keycloakProperties;

    public KeycloakServiceImpl(KeycloakProperties keycloakProperties) {
        this.keycloakProperties = keycloakProperties;
    }

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

    @Override
    public void userCreate(AccountDTO accountDTO) {

        CredentialRepresentation credential = new CredentialRepresentation();
        credential.setType(CredentialRepresentation.PASSWORD);
        credential.setTemporary(false);
        credential.setValue(accountDTO.getPassword());

        UserRepresentation keycloakUser = new UserRepresentation();
        keycloakUser.setUsername(accountDTO.getEmail());
        keycloakUser.setFirstName(accountDTO.getFirstName());
        keycloakUser.setLastName(accountDTO.getLastName());
        keycloakUser.setEmail(accountDTO.getEmail());
        keycloakUser.setCredentials(List.of(credential));
        keycloakUser.setEmailVerified(true);
        keycloakUser.setEnabled(true);

        try (Keycloak keycloak = getKeycloakInstance()) {

            RealmResource realmResource = keycloak.realm(keycloakProperties.getRealm());

            UsersResource usersResource = realmResource.users();

            usersResource.create(keycloakUser);

        }

    }

    public void enableUser(String username) {

        Keycloak keycloak = getKeycloakInstance();

        RealmResource realmResource = keycloak.realm(keycloakProperties.getRealm());

        List<UserRepresentation> users = realmResource.users().search(username);

        if (users.isEmpty()) {
            throw new UserNotFoundException("The user account does not exist: " + username);
        }

        UserRepresentation user = users.get(0);
        user.setEnabled(true);

        realmResource.users().get(user.getId()).update(user);

    }

    public void resetUserPassword(String username, String newPassword) {

        try (Keycloak keycloak = getKeycloakInstance()) {

            RealmResource realmResource = keycloak.realm(keycloakProperties.getRealm());

            UsersResource usersResource = realmResource.users();

            List<UserRepresentation> users = usersResource.search(username);

            if (users.isEmpty()) {
                throw new UserNotFoundException("The user account does not exist: " + username);
            }

            String userId = users.get(0).getId();

            CredentialRepresentation newCredential = new CredentialRepresentation();
            newCredential.setType(CredentialRepresentation.PASSWORD);
            newCredential.setTemporary(false);
            newCredential.setValue(newPassword);

            usersResource.get(userId).resetPassword(newCredential);

        }

    }

    private Keycloak getKeycloakInstance() {

        return Keycloak.getInstance(
                keycloakProperties.getAuthServerUrl(),
                keycloakProperties.getMasterRealm(),
                keycloakProperties.getMasterUser(),
                keycloakProperties.getMasterUserPswd(),
                keycloakProperties.getMasterClient());

    }

}
