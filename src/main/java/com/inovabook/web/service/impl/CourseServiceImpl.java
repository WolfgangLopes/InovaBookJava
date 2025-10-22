package com.inovabook.web.service.impl;

import com.inovabook.web.model.Course;
import com.inovabook.web.repository.CourseRepository;
import com.inovabook.web.service.CourseService;
import com.inovabook.web.dto.CourseDto;
import com.inovabook.web.service.FileStorageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final FileStorageService fileStorageService;

    public CourseServiceImpl(CourseRepository courseRepository, FileStorageService fileStorageService) {
        this.courseRepository = courseRepository;
        this.fileStorageService = fileStorageService;
    }

    @Override
    public List<CourseDto> getAllCourses() {
        List<Course> courses = courseRepository.findAll();
        return courses.stream().map(this::mapToCourseDto).collect(Collectors.toList());
    }

    @Override
    public Course saveCourse(CourseDto courseDto, MultipartFile file) {
        Course course = mapToCourse(courseDto);
            if (file != null && !file.isEmpty()) {
                String filename = fileStorageService.storeFile(file, "image/thumbnail");
                courseDto.setThumbnailPath(filename);
            }
            return courseRepository.save(course);
    }

    @Override
    public CourseDto findById(long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + id));
        return mapToCourseDto(course);
    }

    @Override
    public void updateCourse(CourseDto courseDto, MultipartFile file) {
        Course existing = courseRepository.findById(courseDto.getId())
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + courseDto.getId()));

        // ✅ Map the incoming DTO to a temporary Course object
        Course updatedData = mapToCourse(courseDto);

        // ✅ Apply only updatable fields
        existing.setTitle(updatedData.getTitle());
        existing.setDescription(updatedData.getDescription());
        existing.setPrice(updatedData.getPrice());
        existing.setDuration(updatedData.getDuration());
        existing.setPublished(updatedData.isPublished());

            // ✅ Handle file upload
            if (file != null && !file.isEmpty()) {
                fileStorageService.deleteFile("image/thumbnail", existing.getThumbnailPath());
                String filename = fileStorageService.storeFile(file, "image/thumbnail");
                existing.setThumbnailPath(filename);
            }
            courseRepository.save(existing);
    }

    private Course mapToCourse(CourseDto course) {
        return Course.builder()
                .id(course.getId())
                .title(course.getTitle())
                .description(course.getDescription())
                .price(course.getPrice())
                .duration(course.getDuration())
                .thumbnailPath(course.getThumbnailPath())
                .published(course.isPublished())
                .build();
    }

    private CourseDto mapToCourseDto (Course course) {
        return CourseDto.builder()
                .id(course.getId())
                .title(course.getTitle())
                .description(course.getDescription())
                .createdAt(course.getCreatedAt())
                .updatedAt(course.getUpdatedAt())
                .price(course.getPrice())
                .thumbnailPath(course.getThumbnailPath())
                .published(course.isPublished())
                .publishedAt(course.getPublishedAt())
                .duration(course.getDuration())
                .build();
    }
}