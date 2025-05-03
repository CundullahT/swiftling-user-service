package com.swiftling.service.impl;

import com.swiftling.entity.Account;
import com.swiftling.entity.Token;
import com.swiftling.enums.TokenType;
import com.swiftling.exception.UserNotFoundException;
import com.swiftling.repository.AccountRepository;
import com.swiftling.repository.TokenRepository;
import com.swiftling.service.EmailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class EmailServiceImpl implements EmailService {

    private final AccountRepository accountRepository;
    private final TokenRepository tokenRepository;
    private final JavaMailSender mailSender;

    public EmailServiceImpl(AccountRepository accountRepository, TokenRepository tokenRepository, JavaMailSender mailSender) {
        this.accountRepository = accountRepository;
        this.tokenRepository = tokenRepository;
        this.mailSender = mailSender;
    }

    @Value("${app.frontend.url}")
    private String frontendUrl;

    @Override
    public void sendSignUpEmail(String email) {

        Account account = accountRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("The user account does not exist: " + email));

        String tokenStr = UUID.randomUUID().toString();

        Token token = new Token();
        token.setToken(tokenStr);
        token.setAccount(account);
        token.setTokenType(TokenType.VERIFICATION);
        token.setExpiryDateTime(LocalDateTime.now().plusDays(1));

        tokenRepository.save(token);

        String verifyLink = frontendUrl + "/auth/verify?token=" + tokenStr;

        String subject = "Verify your account";
        String content = String.format("""
            Hello,

            Please verify your account by clicking the link below:
            %s

            This link will expire in 24 hours.

            Regards,
            The SwiftLing Team
            """, verifyLink);

        sendEmail(email, subject, content);

    }

    @Override
    public void sendWelcomeEmail(String email) {

        String subject = "Welcome to SwiftLing App!";
        String content = """
                Hello,

                Your account has been successfully verified! 🎉

                You can now log in and start using all features.

                Regards,
                The SwiftLing Team
                """;

        sendEmail(email, subject, content);

    }

    @Override
    public void sendForgotPasswordEmail(String email) {

        Account account = accountRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("The user account does not exist: " + email));

        String tokenStr = UUID.randomUUID().toString();

        Token token = new Token();
        token.setToken(tokenStr);
        token.setAccount(account);
        token.setTokenType(TokenType.RESET_PASS);
        token.setExpiryDateTime(LocalDateTime.now().plusHours(1));

        tokenRepository.save(token);

        String resetLink = frontendUrl + "/auth/forgot-password?token=" + tokenStr;

        String subject = "Reset your password";
        String content = String.format("""
            Hello,

            We received a request to reset your password.

            You can reset your password by clicking the link below:
            %s

            If you did not request this, you can safely ignore this email.

            This link is valid for 1 hour.

            Regards,
            The SwiftLing Team
            """, resetLink);

        sendEmail(email, subject, content);

    }

    @Override
    public void sendPasswordChangedEmail(String email) {

        String supportEmail = "support@";

        if (frontendUrl.contains("http://")) {
            supportEmail += frontendUrl.replace("http://", "");
        } else {
            supportEmail += frontendUrl.replace("https://", "");
        }

        String subject = "Your password has been changed";
        String content = String.format("""
            Hello,

            This is a confirmation that your password has been changed.

            If you did not make this change, please contact support immediately.
            You can reach out to support team by using this email address: %s

            Regards,
            The SwiftLing Team
            """, supportEmail);

        sendEmail(email, subject, content);

    }

    private void sendEmail(String to, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);
        mailSender.send(message);
    }

}
