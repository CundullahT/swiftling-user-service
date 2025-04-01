package com.swiftling.service.impl;

import com.swiftling.dto.UserDTO;
import com.swiftling.service.AdminService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        return null;
    }

    @Override
    public UserDTO readUserByUsername(String username) {
        return null;
    }

    @Override
    public List<UserDTO> readAllUsers() {
        return List.of();
    }

    @Override
    public UserDTO updateUser(String username, UserDTO userDTO) {
        return null;
    }

    @Override
    public void enableUser(String username) {

    }

    @Override
    public void disableUser(String username) {

    }

    @Override
    public void deleteUser(String username) {

    }

}
