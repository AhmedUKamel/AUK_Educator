package com.ahmedukamel.educator.controller;

import com.ahmedukamel.educator.dto.UserResponse;
import com.ahmedukamel.educator.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final UserService userService;

    @GetMapping
    public String homePage(Model model) {
        List<UserResponse> instructors = userService.getInstructors();
        model.addAttribute("instructors", instructors);
        return "home";
    }
}
