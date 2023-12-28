package com.ahmedukamel.educator.mapper;

import com.ahmedukamel.educator.dto.UserResponse;
import com.ahmedukamel.educator.model.AppUser;

public class UserMapper {
    public static UserResponse mapToResponse(AppUser user) {
        return UserResponse
                .builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .picture(user.getPicture())
                .title(user.getTitle())
                .registration(user.getRegistration())
                .isInstructor(user.getInstructor())
                .build();
    }
}
