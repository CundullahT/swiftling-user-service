package com.swiftling.service.impl;

import com.swiftling.dto.AccountDTO;
import com.swiftling.entity.Account;
import com.swiftling.entity.Token;
import com.swiftling.exception.*;
import com.swiftling.repository.AccountRepository;
import com.swiftling.repository.TokenRepository;
import com.swiftling.service.AccountService;
import com.swiftling.service.EmailService;
import com.swiftling.service.KeycloakService;
import com.swiftling.util.MapperUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final MapperUtil mapperUtil;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;
    private final KeycloakService keycloakService;
    private final TokenRepository tokenRepository;

    public AccountServiceImpl(AccountRepository accountRepository, MapperUtil mapperUtil, EmailService emailService, PasswordEncoder passwordEncoder, KeycloakService keycloakService, TokenRepository tokenRepository) {
        this.accountRepository = accountRepository;
        this.mapperUtil = mapperUtil;
        this.emailService = emailService;
        this.passwordEncoder = passwordEncoder;
        this.keycloakService = keycloakService;
        this.tokenRepository = tokenRepository;
    }

    @Override
    public AccountDTO signUp(AccountDTO accountDTO) {

        accountRepository.findByEmail(accountDTO.getEmail()).ifPresent(acc -> {
            String message = "The user account with the given email address (" + acc.getEmail() + ") already exists.";
            throw new UserAlreadyExistsException(message);
        });

        Account accountToSave = mapperUtil.convert(accountDTO, new Account());

        accountToSave.setIsDeleted(false);
        accountToSave.setIsEnabled(false);

        keycloakService.userCreate(accountDTO);

        accountToSave.setPassword(passwordEncoder.encode(accountDTO.getPassword()));

        Account savedAccount = accountRepository.save(accountToSave);

        emailService.sendSignUpEmail(savedAccount.getEmail());

        return accountDTO;

    }

    @Override
    public void enable(String token) {

        Token existingToken = tokenRepository.findByToken(token)
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

        emailService.sendWelcomeEmail(account.getEmail());

    }

    @Override
    public void forgotPassword(String email) {

        Account account = accountRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("The user account does not exist: " + email));

        emailService.sendForgotPasswordEmail(account.getEmail());

    }

    @Override
    public void resetPassword(String token, String newPassword) {

        Token existingToken = tokenRepository.findByToken(token)
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

}
