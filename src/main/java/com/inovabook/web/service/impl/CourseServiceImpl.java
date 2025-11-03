package com.inovabook.web.service.impl;

import com.inovabook.web.exception.CourseNotFoundException;
import com.inovabook.web.mapper.CourseMapper;
import com.inovabook.web.model.Course;
import com.inovabook.web.repository.CourseRepository;
import com.inovabook.web.repository.EnrollmentRepository;
import com.inovabook.web.repository.LessonRepository;
import com.inovabook.web.service.CourseService;
import com.inovabook.web.dto.CourseDto;
import com.inovabook.web.service.FileStorageService;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import java.util.stream.Collectors;

import static com.inovabook.web.mapper.CourseMapper.mapToCourse;
import static com.inovabook.web.mapper.CourseMapper.mapToCourseDto;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final FileStorageService fileStorageService;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository, FileStorageService fileStorageService, EnrollmentRepository enrollmentRepository, EntityManager entityManager, LessonRepository lessonRepository) {
        this.courseRepository = courseRepository;
        this.fileStorageService = fileStorageService;
    }

    @Override
    public List<CourseDto> getAllCourses() {
        List<Course> courses = courseRepository.findAll();
        return courses.stream().map(CourseMapper::mapToCourseDto).collect(Collectors.toList());
    }

    @Override
    public Course saveCourse(CourseDto courseDto, MultipartFile file) {
        Course course = mapToCourse(courseDto);
            if (file != null && !file.isEmpty()) {
                String filename = fileStorageService.storeFile(file, "image/thumbnail");
                course.setThumbnailPath(filename);
            }
            return courseRepository.save(course);
    }

    @Override
    public CourseDto findById(long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException(id));
        return mapToCourseDto(course);
    }

    @Override
    @Transactional
    public void updateCourse(CourseDto courseDto, MultipartFile file) {
        Course existing = courseRepository.findById(courseDto.getId())
                .orElseThrow(() -> new CourseNotFoundException(courseDto.getId()));

        //  Map the incoming DTO to a temporary Course object
        Course updatedData = CourseMapper.mapToCourse(courseDto);

        //  Update only safe fields
        existing.setTitle(updatedData.getTitle());
        existing.setDescription(updatedData.getDescription());
        existing.setPrice(updatedData.getPrice());
        existing.setDuration(updatedData.getDuration());
        existing.setPublished(updatedData.isPublished());

            if (file != null && !file.isEmpty()) {
                String newFileName = fileStorageService.replaceFile("image/thumbnail", existing.getThumbnailPath(), file);
                existing.setThumbnailPath(newFileName);
            }
            courseRepository.save(existing);
    }

    @Override
    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }

    @Override
    public List<CourseDto> searchCourse(String query) {
        List<Course> courses = courseRepository.searchCourse(query);
        return courses.stream().map(CourseMapper::mapToCourseDto).collect(Collectors.toList());
    }
}