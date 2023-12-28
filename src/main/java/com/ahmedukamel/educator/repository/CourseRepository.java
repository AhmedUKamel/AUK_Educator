package com.ahmedukamel.educator.repository;

import com.ahmedukamel.educator.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    @Query("SELECT c FROM Course c WHERE LOWER(c.name) LIKE CONCAT('%', :search, '%') OR LOWER(c.description) LIKE CONCAT('%', :search, '%')")
    List<Course> searchCourse(@Param("search") String search);

    List<Course> findAllByInstructor_Id(Long instructorId);
}
