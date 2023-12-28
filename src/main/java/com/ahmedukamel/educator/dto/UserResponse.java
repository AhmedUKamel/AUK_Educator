package com.ahmedukamel.educator.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class UserResponse {
    private Long id;
    private String name;
    private String email;
    private String picture;
    private String title;
    private LocalDateTime registration;
    private Boolean isInstructor;
}
