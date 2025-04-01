package com.swiftling.service;

import com.swiftling.dto.UserDTO;

import java.util.List;

public interface AdminService {

    UserDTO createUser(UserDTO userDTO);
    UserDTO readUserByUsername(String username);
    List<UserDTO> readAllUsers();
    UserDTO updateUser(String username, UserDTO userDTO);
    void enableUser(String username);
    void disableUser(String username);
    void deleteUser(String username);

}
