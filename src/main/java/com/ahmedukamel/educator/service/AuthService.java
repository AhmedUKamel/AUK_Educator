package com.ahmedukamel.educator.service;

import jakarta.servlet.http.HttpServletRequest;

public interface AuthService {
    boolean login(HttpServletRequest request, String email, String password);

    void register(HttpServletRequest request, String finalEmail, String password, String name, String title);
}
