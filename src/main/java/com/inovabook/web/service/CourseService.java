package com.inovabook.web.service;

import com.inovabook.web.dto.CourseDto;
import com.inovabook.web.model.Course;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CourseService {
    List<CourseDto> getAllCourses();
    Course saveCourse(Course course, MultipartFile file);

    CourseDto findById(long id);

    void updateCourse(CourseDto course, MultipartFile file);
}
