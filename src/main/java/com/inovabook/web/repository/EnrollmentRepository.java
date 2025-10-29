package com.inovabook.web.repository;

import com.inovabook.web.model.Course;
import com.inovabook.web.model.Enrollment;
import com.inovabook.web.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    boolean existsByUserAndCourse(User user, Course course);
    Optional<Enrollment> findByUserAndCourse(User user, Course course);
}

