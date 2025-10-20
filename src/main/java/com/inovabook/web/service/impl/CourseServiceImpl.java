package com.inovabook.web.service.impl;

import com.inovabook.web.model.Course;
import com.inovabook.web.repository.CourseRepository;
import com.inovabook.web.service.CourseService;

import com.inovabook.web.dto.CourseDto;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {

    private CourseRepository courseRepository;

    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public List<CourseDto> getAllCourses() {
        List<Course> courses = courseRepository.findAll();
        return courses.stream().map(this::mapToCourseDto).collect(Collectors.toList());
    }

    /*@Override
    public Course saveCourse(Course course, MultipartFile file) {
        return courseRepository.save(course);
    }*/

    @Override
    public Course saveCourse(Course course, MultipartFile file) {
        try {
            if (!file.isEmpty()) {

                Path uploadPath = Paths.get("uploads/image/thumbnail");
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }

                String originalFilename = StringUtils.cleanPath(
                        Objects.requireNonNull(file.getOriginalFilename())
                );

                String filename = originalFilename;
                Path filePath = uploadPath.resolve(filename);
                int counter = 1;

                while (Files.exists(filePath)) {
                    int dotIndex = originalFilename.lastIndexOf(".");
                    String base = (dotIndex > 0)
                            ? originalFilename.substring(0, dotIndex)
                            : originalFilename;
                    String ext = (dotIndex > 0)
                            ? originalFilename.substring(dotIndex)
                            : "";
                    filename = base + "(" + counter + ")" + ext;
                    filePath = uploadPath.resolve(filename);
                    counter++;
                }

                try (InputStream inputStream = file.getInputStream()) {
                    Files.copy(inputStream, filePath);
                }

                course.setThumbnailPath(filename);
            }

            return courseRepository.save(course);

        } catch (IOException e) {
            throw new RuntimeException("Failed to save course thumbnail: " + e.getMessage(), e);
        }
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
                .build();
    }
}