package com.swiftling.service.impl;

import com.swiftling.dto.UserDTO;
import com.swiftling.service.RootService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RootServiceImpl implements RootService {

    @Override
    public UserDTO createAdmin(UserDTO userDTO) {
        return null;
    }

    @Override
    public UserDTO readAdminByUsername(String username) {
        return null;
    }

    @Override
    public List<UserDTO> readAllAdmins() {
        return List.of();
    }

    @Override
    public UserDTO updateAdmin(String username, UserDTO userDTO) {
        return null;
    }

    @Override
    public UserDTO enableAdmin(String username, UserDTO userDTO) {
        return null;
    }

    @Override
    public UserDTO disableAdmin(String username, UserDTO userDTO) {
        return null;
    }

    @Override
    public void deleteAdmin(String username) {

    }

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
    public UserDTO enableUser(String username, UserDTO userDTO) {
        return null;
    }

    @Override
    public UserDTO disableUser(String username, UserDTO userDTO) {
        return null;
    }

    @Override
    public void deleteUser(String username) {

    }

}
