package com.example.footballanalyzer.service;

import com.example.footballanalyzer.dto.UserDTO;
import com.example.footballanalyzer.model.User;
import com.example.footballanalyzer.enums.Role;
import com.example.footballanalyzer.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImp implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream().map(User::toUserDTO).sorted().collect(Collectors.toList());
    }
    @Transactional
    public UserDTO createUser(UserDTO userDTO) {
        String email = userDTO.getEmail();
        if (userRepository.findByEmail(userDTO.getEmail()) != null) return userDTO;
        User user = User.builder()
                .name(userDTO.getName())
                .phoneNumber(userDTO.getPhoneNumber())
                .email(userDTO.getEmail())
                .build();
        user.setActive(true);
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.getRoles().add(Role.ROLE_USER);
        userRepository.save(user);
        log.info("Saving new User with email: {}", email);
        return userDTO;
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }


    @Override
    public void userBan(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            if (user.isActive()) {
                user.setActive(false);
                log.info("Ban user with id = {}; email: {}", user.getId(), user.getEmail());
            } else {
                user.setActive(true);
                log.info("Unban user with id = {}; email: {}", user.getId(), user.getEmail());
            }
            userRepository.save(user);
        }
    }

    @Transactional
    @Override
    public void deleteUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            log.info("Deleted User. Name: {}. Email: {}", user.get().getName(), user.get().getEmail());
            userRepository.deleteById(id);
        } else {
            log.error("Error! User not found!");
        }
    }

    @Override
    public void changeUserRoles(Long id, Map<String, String> form) {
        User user = userRepository
                .findById(id)
                .orElse(null);
        if (user != null) {
            Set<String> roles = Arrays.stream(Role.values())
                    .map(Role::name)
                    .collect(Collectors.toSet());
            user.getRoles().clear();
            for (String key : form.keySet()) {
                if (roles.contains(key)) {
                    user.getRoles().add(Role.valueOf(key));
                }
            }
            userRepository.save(user);
        }
    }

    @Override
    public User getUserByPrincipal(Principal principal) {
        if (principal == null) return new User();
        return userRepository.findByEmail(principal.getName());
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO, Long id) {
        User user = userRepository
                .findById(id)
                .orElse(null);
        if (user != null) {
            user.setName(userDTO.getName());
            user.setEmail(userDTO.getEmail());
            user.setPhoneNumber(userDTO.getPhoneNumber());
            if (!passwordEncoder.matches(passwordEncoder.encode(userDTO.getPassword()), user.getPassword())) {
                user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            }
            userRepository.save(user);
        }
        return userDTO;
    }
}
