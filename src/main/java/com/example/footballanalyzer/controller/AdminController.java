package com.example.footballanalyzer.controller;

import com.example.footballanalyzer.dto.ClubDTO;
import com.example.footballanalyzer.dto.UserDTO;
import com.example.footballanalyzer.model.Club;
import com.example.footballanalyzer.model.User;
import com.example.footballanalyzer.enums.Role;
import com.example.footballanalyzer.service.ClubService;
import com.example.footballanalyzer.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class AdminController {
    private final UserService userService;
    private final ClubService clubService;

    @GetMapping("/admin")
    public String admin(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "admin";
    }

    @PostMapping("/admin/user/ban/{id}")
    public String userBan(@PathVariable("id") Long id) {
        userService.userBan(id);
        return "redirect:/admin";
    }

    @GetMapping("/admin/user/edit/{user}")
    public String userEdit(@PathVariable("user") User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "user-edit";
    }

    @PostMapping("/admin/user/edit")
    public String userEdit(@RequestParam("userId") UserDTO userDTO,
                           @RequestParam Map<String, String> form,
                           Model model) {
        userService.changeUserRoles(userDTO.getId(), form);
        UserDTO user = userService.updateUser(userDTO, userDTO.getId());
        model.addAttribute("user", user);
        return "redirect:/admin";
    }

    @PostMapping("/admin/user/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/";
    }

    @PostMapping("/club/create")
    public String createClub(ClubDTO clubDTO, Model model) {
        ClubDTO club = clubService.createClub(clubDTO);
        model.addAttribute("club", club);
        return "redirect:/";
    }

    @PostMapping("/club/delete/{id}")
    public String deleteClub(@PathVariable Long id) {
        clubService.deleteClub(id);
        return "redirect:/";
    }

    @GetMapping("/admin/club/edit/{club}")
    public String clubEdit(@PathVariable("club") ClubDTO clubDTO, Model model) {
        ClubDTO club = clubService.updateClub(clubDTO, clubDTO.getId());
        model.addAttribute("club", club);
        //model.addAttribute("logotype", clubDTO.getLogotype());
        return "club-edit";
    }
}
