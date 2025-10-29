package com.inovabook.web.service;

import com.inovabook.web.dto.CourseDto;
import com.inovabook.web.model.Course;
import com.inovabook.web.model.User;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface CourseService {
    List<CourseDto> getAllCourses();

    Course saveCourse(CourseDto courseDto, MultipartFile file);

    CourseDto findById(long id);

    void updateCourse(CourseDto course, MultipartFile file);

    void deleteCourse(Long id);

    List<CourseDto> searchCourse(String query);
}
