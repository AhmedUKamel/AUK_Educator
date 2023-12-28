package com.ahmedukamel.educator.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String quote;
    private String thumbnail;
    @CreationTimestamp
    private LocalDateTime createdOn;
    @UpdateTimestamp
    private LocalDateTime updatedOn;
    @ManyToOne
    @JoinColumn(name = "instructor_id", nullable = false)
    private AppUser instructor;
    @OneToMany(mappedBy = "course", cascade = CascadeType.REMOVE)
    private List<Chapter> chapters;
    @OneToMany(mappedBy = "course", cascade = CascadeType.REMOVE)
    private List<Feature> features;
}
