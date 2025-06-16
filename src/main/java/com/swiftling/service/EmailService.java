package com.swiftling.service;

public interface EmailService {

    void sendVerifyEmail(String email);
    void sendVerificationConfirmedEmail(String email);
    void sendForgotPasswordEmail(String email);
    void sendPasswordChangedEmail(String email);
    void sendAccountUpdatedEmailToOldEmail(String oldEmail);
    void sendEmailChangeConfirmationToNewEmail(String newEmail);

}
