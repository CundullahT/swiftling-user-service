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
    public void sendVerifyEmail(String email) {

        Account account = accountRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("The user account does not exist: " + email));

        String tokenStr = UUID.randomUUID().toString();

        Token token = new Token();
        token.setToken(tokenStr);
        token.setAccount(account);
        token.setTokenType(TokenType.VERIFICATION);
        token.setExpiryDateTime(LocalDateTime.now().plusDays(1));
        token.setIsDeleted(false);
        token.setIsEnabled(true);

        tokenRepository.save(token);

        String verifyLink = frontendUrl + "/auth/verify?token=" + tokenStr;

        String subject = "Verify your email address";
        String content = String.format("""
            Hello,
            
            Please verify your email address by clicking the link below:
            %s
            
            This link will expire in 24 hours.
            
            Regards,
            The SwiftLing Team
            """, verifyLink);

        sendEmail(email, subject, content);

    }

    @Override
    public void sendVerificationConfirmedEmail(String email) {

        String subject = "Confirmation of the verification of your email address";
        String content = """
            Hello,
            
            Your email address has been successfully verified! 🎉
            
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
        token.setIsDeleted(false);
        token.setIsEnabled(true);

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

        String subject = "Your password has been changed";
        String content = String.format("""
            Hello,
            
            This is a confirmation that your password has been changed.
            
            If you made these changes, you can safely ignore this message.
            If you did not make this change, please contact support immediately.
            You can reach out to support team by using this email address: %s
            
            Regards,
            The SwiftLing Team
            """, getSupportEmailAddress());

        sendEmail(email, subject, content);

    }

    @Override
    public void sendAccountUpdatedEmailToOldEmail(String oldEmail) {

        String subject = "Your account has been updated";
        String content = String.format("""
            Hello,
            
            This is to inform you that changes were made to your account.
            
            If you made these changes, you can safely ignore this message.
            If you did not make this change, please contact support immediately.
            You can reach out to support team by using this email address: %s
            
            Regards,
            The SwiftLing Team
            """, getSupportEmailAddress());

        sendEmail(oldEmail, subject, content);

    }

    @Override
    public void sendAccountDeletedEmail(String email) {

        String subject = "Your account has been deleted";
        String content = String.format("""
        Hello,
        
        This is a confirmation that your SwiftLing account has been permanently deleted.
        
        If you requested this deletion, no further action is required.
        If you did not initiate this deletion, please contact support immediately.
        You can reach out to the support team by using this email address: %s
        
        Regards,
        The SwiftLing Team
        """, getSupportEmailAddress());

        sendEmail(email, subject, content);

    }

    private void sendEmail(String to, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);
        mailSender.send(message);
    }

    private String getSupportEmailAddress() {

        String supportEmail = "support@";

        if (frontendUrl.contains("http://")) {
            supportEmail += frontendUrl.replace("http://", "");
        } else {
            supportEmail += frontendUrl.replace("https://", "");
        }

        return supportEmail;

    }

}
