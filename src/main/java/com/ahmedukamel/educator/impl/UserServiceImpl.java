package com.ahmedukamel.educator.impl;

import com.ahmedukamel.educator.dto.UserResponse;
import com.ahmedukamel.educator.mapper.UserMapper;
import com.ahmedukamel.educator.model.AppUser;
import com.ahmedukamel.educator.repository.UserRepository;
import com.ahmedukamel.educator.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public List<UserResponse> getUsers() {
        return userRepository.findAll().stream().map(UserMapper::mapToResponse).toList();
    }

    @Override
    public List<UserResponse> getInstructors() {
        return userRepository.findAllByInstructor(true).stream().map(UserMapper::mapToResponse).toList();
    }

    @Override
    public List<UserResponse> getStudents() {
        return userRepository.findAllByInstructor(false).stream().map(UserMapper::mapToResponse).toList();
    }

    @Override
    public UserResponse getUserResponse(String email) {
        return UserMapper.mapToResponse(userRepository.findByEmail(email).orElse(null));
    }

    @Override
    public Boolean isEmailExist(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public Optional<AppUser> getUser(String email) {
        return userRepository.findByEmail(email);
    }
}
