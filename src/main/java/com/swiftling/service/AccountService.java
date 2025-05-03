package com.swiftling.service;

import com.swiftling.dto.AccountDTO;

public interface AccountService {

    AccountDTO signUp(AccountDTO accountDTO);

    void enable(String token);

    void forgotPassword(String email);

    void resetPassword(String token, String newPassword);

}
