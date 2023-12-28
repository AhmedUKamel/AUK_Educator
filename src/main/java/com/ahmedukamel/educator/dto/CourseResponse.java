package com.ahmedukamel.educator.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CourseResponse {
    private Long id;
    private String name;
    private String description;
    private String quote;
    private String thumbnail;
    private String createdOn;
    private UserResponse instructor;
}
