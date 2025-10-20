package com.inovabook.web.repository;

import com.inovabook.web.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long>{
    Optional<Course> findByTitle(String title);
}
