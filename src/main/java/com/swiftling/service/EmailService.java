package com.swiftling.service;

public interface EmailService {

    void sendSignUpEmail(String email);
    void sendWelcomeEmail(String email);
    void sendForgotPasswordEmail(String email);
    void sendPasswordChangedEmail(String email);
    void sendAccountUpdatedEmailToOldEmail(String oldEmail);
    void sendEmailChangeConfirmationToNewEmail(String newEmail);

}
