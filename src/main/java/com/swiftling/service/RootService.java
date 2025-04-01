package com.swiftling.service;

import com.swiftling.dto.UserDTO;

import java.util.List;

public interface RootService {

    UserDTO createAdmin(UserDTO userDTO);
    UserDTO readAdmin(String username);
    List<UserDTO> readAllAdmin();
    UserDTO updateAdmin(String username, UserDTO userDTO);
    UserDTO enableAdmin(String username, UserDTO userDTO);
    UserDTO disableAdmin(String username, UserDTO userDTO);
    void deleteAdmin(String username);

    UserDTO createUser(UserDTO userDTO);
    UserDTO readUser(String username);
    List<UserDTO> readAllUser();
    UserDTO updateUser(String username, UserDTO userDTO);
    UserDTO enableUser(String username, UserDTO userDTO);
    UserDTO disableUser(String username, UserDTO userDTO);
    void deleteUser(String username);

}
