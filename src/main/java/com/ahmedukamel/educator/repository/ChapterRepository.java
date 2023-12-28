package com.ahmedukamel.educator.repository;

import com.ahmedukamel.educator.model.Chapter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChapterRepository extends JpaRepository<Chapter, Long> {
    List<Chapter> findAllByCourse_Id(Long courseId);
}
