package com.ahmedukamel.educator.impl;

import com.ahmedukamel.educator.dto.CourseResponse;
import com.ahmedukamel.educator.mapper.CourseMapper;
import com.ahmedukamel.educator.model.Chapter;
import com.ahmedukamel.educator.model.Course;
import com.ahmedukamel.educator.model.Feature;
import com.ahmedukamel.educator.repository.ChapterRepository;
import com.ahmedukamel.educator.repository.CourseRepository;
import com.ahmedukamel.educator.repository.FeatureRepository;
import com.ahmedukamel.educator.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final ChapterRepository chapterRepository;
    private final FeatureRepository featureRepository;

    @Override
    public List<CourseResponse> getCourses() {
        return courseRepository.findAll().stream().map(CourseMapper::mapToResponse).toList();
    }

    @Override
    public List<CourseResponse> getInstructorCourses(Long instructorId) {
        return courseRepository.findAllByInstructor_Id(instructorId).stream().map(CourseMapper::mapToResponse).toList();
    }

    @Override
    public List<CourseResponse> searchCourses(String search) {
        return courseRepository.searchCourse(search).stream().map(CourseMapper::mapToResponse).toList();
    }

    @Override
    public List<Chapter> getChapters(Long courseId) {
        return chapterRepository.findAllByCourse_Id(courseId);
    }

    @Override
    public List<Feature> getFeatures(Long courseId) {
        return featureRepository.findAllByCourse_Id(courseId);
    }

    @Override
    public CourseResponse getCourse(Long courseId) {
        return CourseMapper.mapToResponse(courseRepository.findById(courseId).orElse(new Course()));
    }
}
