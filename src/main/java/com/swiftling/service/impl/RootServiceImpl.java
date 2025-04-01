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
    public UserDTO readAdmin(String username) {
        return null;
    }

    @Override
    public List<UserDTO> readAllAdmin() {
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
    public UserDTO readUser(String username) {
        return null;
    }

    @Override
    public List<UserDTO> readAllUser() {
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
