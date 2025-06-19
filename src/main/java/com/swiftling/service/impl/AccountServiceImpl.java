package com.swiftling.service.impl;

import com.swiftling.client.NotificationClient;
import com.swiftling.client.PhraseClient;
import com.swiftling.client.QuizClient;
import com.swiftling.dto.AccountDTO;
import com.swiftling.dto.UpdateAccountRequestDTO;
import com.swiftling.dto.UserIdEmailRequestDTO;
import com.swiftling.entity.Account;
import com.swiftling.entity.Token;
import com.swiftling.enums.TokenType;
import com.swiftling.exception.*;
import com.swiftling.repository.AccountRepository;
import com.swiftling.repository.TokenRepository;
import com.swiftling.service.AccountService;
import com.swiftling.service.EmailService;
import com.swiftling.service.KeycloakService;
import com.swiftling.util.MapperUtil;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final MapperUtil mapperUtil;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;
    private final KeycloakService keycloakService;
    private final TokenRepository tokenRepository;
    private final NotificationClient notificationClient;
    private final PhraseClient phraseClient;
    private final QuizClient quizClient;

    public AccountServiceImpl(AccountRepository accountRepository, MapperUtil mapperUtil, EmailService emailService, PasswordEncoder passwordEncoder, KeycloakService keycloakService, TokenRepository tokenRepository, NotificationClient notificationClient, PhraseClient phraseClient, QuizClient quizClient) {
        this.accountRepository = accountRepository;
        this.mapperUtil = mapperUtil;
        this.emailService = emailService;
        this.passwordEncoder = passwordEncoder;
        this.keycloakService = keycloakService;
        this.tokenRepository = tokenRepository;
        this.notificationClient = notificationClient;
        this.phraseClient = phraseClient;
        this.quizClient = quizClient;
    }

    @Override
    public AccountDTO signUp(AccountDTO accountDTO) {

        accountRepository.findByEmail(accountDTO.getEmail()).ifPresent(acc -> {
            String message = "The user account with the given email address (" + acc.getEmail() + ") already exists.";
            throw new UserAlreadyExistsException(message);
        });

        Account accountToSave = mapperUtil.convert(accountDTO, new Account());

        accountToSave.setExternalId(UUID.randomUUID());
        accountToSave.setIsDeleted(false);
        accountToSave.setIsEnabled(false);

        keycloakService.userCreate(accountDTO);

        accountToSave.setPassword(passwordEncoder.encode(accountDTO.getPassword()));

        Account savedAccount = accountRepository.save(accountToSave);

        UserIdEmailRequestDTO userIdEmailRequestDTO = new UserIdEmailRequestDTO();
        userIdEmailRequestDTO.setExternalId(savedAccount.getExternalId());
        userIdEmailRequestDTO.setEmail(savedAccount.getEmail());

        notificationClient.createUserIdEmail(userIdEmailRequestDTO);

        emailService.sendVerifyEmail(savedAccount.getEmail());

        return accountDTO;

    }

    @Transactional
    @Override
    public void enable(String token) {

        Token existingToken = tokenRepository.findByTokenAndTokenType(token, TokenType.VERIFICATION)
                .orElseThrow(() -> new TokenNotFoundException("The token does not exist."));

        if (existingToken.getExpiryDateTime().isBefore(LocalDateTime.now()) || existingToken.getIsDeleted()) {
            throw new TokenExpiredException("The token has expired.");
        }

        Account account = existingToken.getAccount();

        if (account.getIsEnabled()) {
            throw new UserAlreadyEnabledException("The user account is already enabled/verified.");
        }

        keycloakService.enableUser(account.getEmail());

        account.setIsEnabled(true);
        accountRepository.save(account);

        tokenRepository.deleteByToken((existingToken.getToken()));

        emailService.sendVerificationConfirmedEmail(account.getEmail());

    }

    @Override
    public void forgotPassword(String email) {

        Account account = accountRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("The user account does not exist: " + email));

        emailService.sendForgotPasswordEmail(account.getEmail());

    }

    @Transactional
    @Override
    public void resetPassword(String token, String newPassword) {

        Token existingToken = tokenRepository.findByTokenAndTokenType(token, TokenType.RESET_PASS)
                .orElseThrow(() -> new TokenNotFoundException("The token does not exist."));

        if (existingToken.getExpiryDateTime().isBefore(LocalDateTime.now()) || existingToken.getIsDeleted()) {
            throw new TokenExpiredException("The token has expired.");
        }

        Account account = existingToken.getAccount();

        keycloakService.resetUserPassword(account.getEmail(), newPassword);

        account.setPassword(passwordEncoder.encode(newPassword));
        accountRepository.save(account);

        tokenRepository.deleteByToken((token));

        emailService.sendPasswordChangedEmail(account.getEmail());

    }

    @Override
    public void changePassword(String currentPassword, String newPassword) {

        String loggedInUserName = keycloakService.getLoggedInUserName();

        Account account = accountRepository.findByEmail(loggedInUserName)
                .orElseThrow(() -> new UserNotFoundException("The user account does not exist: " + loggedInUserName));

        if (!passwordEncoder.matches(currentPassword, account.getPassword())) {
            throw new PasswordIncorrectException("The given current password is incorrect.");
        } else {

            keycloakService.resetUserPassword(loggedInUserName, newPassword);

            account.setPassword(passwordEncoder.encode(newPassword));
            accountRepository.save(account);

            emailService.sendPasswordChangedEmail(loggedInUserName);

        }

    }

    @Override
    public AccountDTO update(UpdateAccountRequestDTO requestDTO) {

        String loggedInEmailUsername = keycloakService.getLoggedInUserName();

        Account foundAccount = accountRepository.findByEmail(loggedInEmailUsername)
                .orElseThrow(() -> new UserNotFoundException("The user account does not exist: " + loggedInEmailUsername));

        if (foundAccount.getIsDeleted()) {
            throw new UserNotFoundException("The user account does not exist: " + loggedInEmailUsername);
        }

        if (!foundAccount.getIsEnabled()) {
            throw new UserNotEnabledException("The user account is not enabled: " + loggedInEmailUsername);
        }

        String oldEmail = foundAccount.getEmail();

        if (requestDTO.getEmail() != null) {
            foundAccount.setEmail(requestDTO.getEmail());
        }
        if (requestDTO.getFirstName() != null) {
            foundAccount.setFirstName(requestDTO.getFirstName());
        }
        if (requestDTO.getLastName() != null) {
            foundAccount.setLastName(requestDTO.getLastName());
        }

        foundAccount.setIsEnabled(false);

        keycloakService.userUpdate(loggedInEmailUsername, requestDTO);

        Account savedAccount = accountRepository.save(foundAccount);

        emailService.sendAccountUpdatedEmailToOldEmail(oldEmail);

        if (!oldEmail.equalsIgnoreCase(requestDTO.getEmail())) {

            UserIdEmailRequestDTO userIdEmailRequestDTO = new UserIdEmailRequestDTO();
            userIdEmailRequestDTO.setExternalId(savedAccount.getExternalId());
            userIdEmailRequestDTO.setEmail(requestDTO.getEmail());

            notificationClient.updateUserIdEmail(userIdEmailRequestDTO);
            emailService.sendVerifyEmail(requestDTO.getEmail());

        }

        return mapperUtil.convert(savedAccount, new AccountDTO());

    }

    @Override
    public void delete() {

        String loggedInEmail = keycloakService.getLoggedInUserName();

        Account accountToDelete = accountRepository.findByEmailAndIsDeleted(loggedInEmail, false)
                .orElseThrow(() -> new UserNotFoundException("The user account does not exist: " + loggedInEmail));

        accountToDelete.setEmail(accountToDelete.getEmail() + "-" + accountToDelete.getId());
        accountToDelete.setIsDeleted(true);

        keycloakService.delete(loggedInEmail);

        try {
            accountRepository.save(accountToDelete);
        } catch (Throwable exception) {
            log.error(exception.getMessage());
            exception.printStackTrace();
            throw new UserCanNotBeDeletedException("The user can not be deleted: " + loggedInEmail);
        }

        deleteUserPhrases(accountToDelete.getExternalId());
        deleteUserQuizzes(accountToDelete.getExternalId());
        deleteNotificationUserIdEmail(accountToDelete.getExternalId(), loggedInEmail);


    }

    private void deleteUserPhrases(UUID externalId) {

        try {
            phraseClient.deleteUserPhrases(externalId);
        } catch (Throwable exception) {
            log.error(exception.getMessage());
            exception.printStackTrace();
            throw new UserPhrasesNotDeletedException("The phrases of the user (" + externalId + ") can not be deleted.");
        }

    }

    private void deleteUserQuizzes(UUID externalId) {

        try {
            quizClient.deleteUserQuizzes(externalId);
        } catch (Throwable exception) {
            log.error(exception.getMessage());
            exception.printStackTrace();
            throw new UserQuizzesNotDeletedException("The quizzes of the user (" + externalId + ") can not be deleted.");
        }

    }

    private void deleteNotificationUserIdEmail(UUID externalId, String loggedInEmail) {

        try {
            notificationClient.deleteUserIdEmail(externalId);
        } catch (Throwable exception) {
            log.error(exception.getMessage());
            exception.printStackTrace();
            throw new UserIdEmailNotDeletedException("The user id " + externalId + " and email " + loggedInEmail + " can not be deleted.");
        }

    }

    @Override
    public UUID getExternalIdOfUserAccount() {

        String email = keycloakService.getLoggedInUserName();

        Account foundAccount = accountRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("The user account does not exist: " + email));

        return foundAccount.getExternalId();

    }

}
