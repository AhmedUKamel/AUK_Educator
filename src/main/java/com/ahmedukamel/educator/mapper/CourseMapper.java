package com.ahmedukamel.educator.mapper;

import com.ahmedukamel.educator.dto.CourseResponse;
import com.ahmedukamel.educator.model.Course;

import java.time.format.DateTimeFormatter;

public class CourseMapper {
    public static CourseResponse mapToResponse(Course course) {
        return CourseResponse
                .builder()
                .id(course.getId())
                .name(course.getName())
                .description(course.getDescription())
                .quote(course.getQuote())
                .thumbnail(course.getThumbnail())
                .createdOn(course.getCreatedOn().format(DateTimeFormatter.ofPattern("MMM, dd yyyy")))
                .instructor(UserMapper.mapToResponse(course.getInstructor()))
                .build();
    }
}
