package com.ahmedukamel.educator.service;

import com.ahmedukamel.educator.dto.CourseResponse;
import com.ahmedukamel.educator.model.Chapter;
import com.ahmedukamel.educator.model.Feature;

import java.util.List;

public interface CourseService {
    List<CourseResponse> getCourses();
    List<CourseResponse> getInstructorCourses(Long instructorId);
    List<CourseResponse> searchCourses(String search);
    List<Chapter> getChapters(Long courseId);
    List<Feature> getFeatures(Long courseId);
    CourseResponse getCourse(Long courseId);
}
