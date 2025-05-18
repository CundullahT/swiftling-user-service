package com.swiftling.service;

import com.swiftling.dto.AccountDTO;
import com.swiftling.dto.UpdateAccountRequestDTO;

public interface AccountService {

    AccountDTO signUp(AccountDTO accountDTO);

    void enable(String token);

    void forgotPassword(String email);

    void resetPassword(String token, String newPassword);

    void changePassword(String currentPassword, String newPassword);

    AccountDTO update(UpdateAccountRequestDTO requestDTO);

    void delete(String email);

}
