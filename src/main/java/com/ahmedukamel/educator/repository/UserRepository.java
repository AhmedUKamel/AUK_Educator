package com.ahmedukamel.educator.repository;

import com.ahmedukamel.educator.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {
    List<AppUser> findAllByInstructor(boolean isInstructor);

    Optional<AppUser> findByEmail(String email);

    Boolean existsByEmail(String email);
}
