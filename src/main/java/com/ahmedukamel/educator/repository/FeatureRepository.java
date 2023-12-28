package com.ahmedukamel.educator.repository;

import com.ahmedukamel.educator.model.Feature;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeatureRepository extends JpaRepository<Feature, Long> {
    List<Feature> findAllByCourse_Id(Long courseId);
}
