package com.example.footballanalyzer.controller;

import com.example.footballanalyzer.dto.ClubDTO;
import com.example.footballanalyzer.model.Club;
import com.example.footballanalyzer.service.ClubServiceImp;
import com.example.footballanalyzer.service.UserServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class ClubController {
    private final ClubServiceImp clubService;
    private final UserServiceImp userService;

    @GetMapping("/")
    public String clubs(@RequestParam(name = "name", required = false) String name, Principal principal, Model model) {
        if (name == null) {
            model.addAttribute("clubs", clubService.getAllClubs());
        } else {
            model.addAttribute("clubs", clubService.getClubsByName(name));
        }
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        return "clubs";
    }


    @GetMapping("/club/{id}")
    public String clubInfo(@PathVariable Long id, Model model) {
        Club club = clubService.getClubById(id);
        model.addAttribute("club", club);
        //model.addAttribute("logotype", club.getLogotype());
        return "club-info";
    }
}
