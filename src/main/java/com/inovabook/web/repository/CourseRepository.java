package com.inovabook.web.repository;

import com.inovabook.web.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long>{
    Optional<Course> findByTitle(String title);

    @Query("SELECT c FROM Course c WHERE c.title LIKE CONCAT('%', :query, '%')")
    List<Course> searchCourse(String query);
}
