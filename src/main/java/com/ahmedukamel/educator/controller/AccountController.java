package com.ahmedukamel.educator.controller;

import com.ahmedukamel.educator.model.AppUser;
import com.ahmedukamel.educator.service.AuthService;
import com.ahmedukamel.educator.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.regex.Pattern;

@Controller
@RequiredArgsConstructor
@RequestMapping("account")
public class AccountController {
    private final UserService userService;
    private final AuthService authService;
    private boolean visitedLogin;
    private boolean visitedRegisterOne;
    private boolean visitedRegisterTwo;
    private boolean finishRegisterOne;

    @GetMapping
    public String getEmailForm(Model model) {
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof AppUser) {
            return "redirect:/";
        }
        model.addAttribute("checkAccount", true);
        return "account";
    }

    @PostMapping
    public String accountCheck(@RequestParam(name = "email") String email,
                               @RequestParam(name = "password", required = false) String password,
                               @RequestParam(name = "confirm", required = false) String confirm,
                               @RequestParam(name = "name", required = false) String name,
                               @RequestParam(name = "title", required = false) String title,
                               Model model,
                               HttpServletRequest request) {
        String finalEmail = email.toLowerCase().strip();
        String regexEmail = "[A-Za-z0-9._]+@gmail.com";
        model.addAttribute("email", finalEmail);
        model.addAttribute("password", password);
        model.addAttribute("confirm", confirm);
        model.addAttribute("name", name);
        model.addAttribute("title", title);
        if (!Pattern.compile(regexEmail).matcher(email).matches()) {
            model.addAttribute("checkAccount", true);
            model.addAttribute("errorEmail", "Email must match example.gmail.com");
            return "account";
        }
        Optional<AppUser> user = userService.getUser(finalEmail);
        if (user.isPresent()) {
            model.addAttribute("login", true);
            if (visitedLogin) {
                if (authService.login(request, finalEmail, password)) {
                    return "redirect:/";
                } else {
                    model.addAttribute("errorPassword", "Invalid password");
                }
            }
            visitedLogin = true;
        } else {
            if (!finishRegisterOne) {
                model.addAttribute("registerOne", true);
                if (visitedRegisterOne) {
                    if (!password.equals(confirm)) {
                        model.addAttribute("errorConfirm", "Confirm password doesn't match password");
                    } else {
                        finishRegisterOne = true;
                        model.addAttribute("registerOne", false);
                    }
                }
                visitedRegisterOne = true;
            }
            if (finishRegisterOne) {
                model.addAttribute("registerTwo", true);
                if (visitedRegisterTwo) {
                    String regex = "[a-zA-Z ]+";
                    boolean invalidName = !Pattern.compile(regex).matcher(name).matches();
                    boolean invalidTitle = !Pattern.compile(regex).matcher(title).matches();
                    boolean nullName = name.isBlank();
                    boolean nullTitle = title.isBlank();
                    if (nullName) {
                        model.addAttribute("errorName", "Name cannot be blank");
                    }
                    if (nullTitle) {
                        model.addAttribute("errorTitle", "Title cannot be blank");
                    }
                    if (invalidName) {
                        model.addAttribute("errorName", "Name can contain only characters");
                    }
                    if (invalidName) {
                        model.addAttribute("errorTitle", "Title can contain only characters");
                    }
                    if (!(invalidName || invalidTitle || nullTitle || nullName)) {
                        authService.register(request, finalEmail, password, name, title);
                        return "redirect:/";
                    }
                }
                visitedRegisterTwo = true;
            }
        }
        return "account";
    }
}
