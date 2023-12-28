package com.ahmedukamel.educator.service;

import com.ahmedukamel.educator.dto.UserResponse;
import com.ahmedukamel.educator.model.AppUser;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserResponse> getUsers();

    List<UserResponse> getInstructors();

    List<UserResponse> getStudents();

    UserResponse getUserResponse(String email);
    Boolean isEmailExist(String email);
    Optional<AppUser> getUser(String email);
}
