package com.ahmedukamel.educator.controller;

import com.ahmedukamel.educator.dto.CourseResponse;
import com.ahmedukamel.educator.impl.CourseServiceImpl;
import com.ahmedukamel.educator.model.Chapter;
import com.ahmedukamel.educator.model.Feature;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("courses")
public class CourseController {
    private final CourseServiceImpl courseService;

    @GetMapping
    public String getCourses(Model model) {
        List<CourseResponse> courses = courseService.getCourses();
        model.addAttribute("courses", courses);
        return "course-list";
    }

    @GetMapping("{courseId}/details")
    public String getCourseDetails(@PathVariable("courseId") Long courseId, Model model) {
        CourseResponse course = courseService.getCourse(courseId);
        List<Chapter> chapters = courseService.getChapters(courseId);
        List<Feature> features = courseService.getFeatures(courseId);
        List<CourseResponse> iCourses = courseService.getInstructorCourses(course.getInstructor().getId());
        model.addAttribute("course", course);
        model.addAttribute("chapters", chapters);
        model.addAttribute("features", features);
        model.addAttribute("iCourses", iCourses);
        return "course-details";
    }

    @GetMapping("search")
    public String searchCourse(@RequestParam(value = "search") String search, Model model) {
        List<CourseResponse> courses = courseService.searchCourses(search.toLowerCase().strip());
        model.addAttribute("courses", courses);
        return "course-list";
    }
}
