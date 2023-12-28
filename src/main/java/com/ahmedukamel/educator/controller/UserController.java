package com.ahmedukamel.educator.controller;

import com.ahmedukamel.educator.model.AppUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("user")
public class UserController {
    @GetMapping("profile")
    public String getProfile(Model model) {
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof AppUser user) {
            model.addAttribute("user", user);
        }
        return "profile";
    }
}
