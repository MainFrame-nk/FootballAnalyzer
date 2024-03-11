package com.example.footballanalyzer.controller;

import com.example.footballanalyzer.dto.UserDTO;
import com.example.footballanalyzer.model.User;
import com.example.footballanalyzer.service.UserServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserServiceImp userService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String createUser(UserDTO userDTO, Model model) {
        UserDTO user = userService.createUser(userDTO);
        if (user != null) {
            model.addAttribute("errorMessage", "Пользователь с email: " + userDTO.getEmail() + " уже существует.");
            return "registration";
        }
        return "redirect:/login";
    }

    @GetMapping("/user/{user}")
    public String userInfo(@PathVariable("user") User user, Model model) {
        model.addAttribute("user", user);
        return "user-info";
    }

}
