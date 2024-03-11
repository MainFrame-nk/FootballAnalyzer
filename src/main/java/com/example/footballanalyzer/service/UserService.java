package com.example.footballanalyzer.service;

import com.example.footballanalyzer.dto.UserDTO;
import com.example.footballanalyzer.model.User;

import java.security.Principal;
import java.util.List;
import java.util.Map;

public interface UserService {
    List<UserDTO> getAllUsers();
    User getUserByEmail(String email);
    UserDTO createUser(UserDTO userDTO);
    User getUserById(Long id);
    void deleteUser(Long id);
    UserDTO updateUser(UserDTO userDTO, Long id);
    void userBan(Long id);
    void changeUserRoles(Long id, Map<String, String> roles);
    User getUserByPrincipal(Principal principal);
}
