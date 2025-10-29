package com.inovabook.web.service.impl;

import com.inovabook.web.dto.CourseSummaryDto;
import com.inovabook.web.model.Course;
import com.inovabook.web.model.Enrollment;
import com.inovabook.web.model.User;
import com.inovabook.web.repository.CourseRepository;
import com.inovabook.web.repository.EnrollmentRepository;
import com.inovabook.web.service.EnrollService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnrollServiceImpl implements EnrollService {


    private final EnrollmentRepository enrollmentRepository;
    private final CourseRepository courseRepository;

    public EnrollServiceImpl(EnrollmentRepository enrollmentRepository, CourseRepository courseRepository) {
        this.enrollmentRepository = enrollmentRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    @Transactional
    public void subscribeUserToCourse(User user, Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException("Course not found"));

        if (!enrollmentRepository.existsByUserAndCourse(user, course)) {
            Enrollment enrollment = new Enrollment(user, course);
            enrollmentRepository.save(enrollment);
        }
    }

    // For Thymeleaf: list all courses with enrollment status
    public List<CourseSummaryDto> getAllCoursesWithEnrollmentStatus(User user) {
        return courseRepository.findAll().stream()
                .map(course -> {
                    boolean enrolled = enrollmentRepository.existsByUserAndCourse(user, course);
                    return new CourseSummaryDto(course.getId(), course.getTitle(), enrolled);
                })
                .toList();
    }
}
