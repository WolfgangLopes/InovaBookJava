package com.inovabook.web.service.impl;

import com.inovabook.web.dto.LessonDto;
import com.inovabook.web.exception.LessonNotFoundException;
import com.inovabook.web.model.Course;
import com.inovabook.web.model.Lesson;
import com.inovabook.web.repository.CourseRepository;
import com.inovabook.web.repository.LessonRepository;
import com.inovabook.web.service.FileStorageService;
import com.inovabook.web.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class LessonServiceImpl implements LessonService {
    private final FileStorageService fileStorageService;
    private LessonRepository lessonRepository;
    private CourseRepository courseRepository;

    @Autowired
    public LessonServiceImpl(LessonRepository lessonRepository, CourseRepository courseRepository, FileStorageService fileStorageService) {
        this.lessonRepository = lessonRepository;
        this.courseRepository = courseRepository;
        this.fileStorageService = fileStorageService;
    }

    @Override
    public void createLesson(Long id,
                             LessonDto lessonDto,
                             MultipartFile videoFile) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new LessonNotFoundException(id));
        if(videoFile != null && !videoFile.isEmpty()){
            String filename = fileStorageService.storeFile(videoFile, "video/lesson");
                    lessonDto.setVideoPath(filename);
        }
                Lesson lesson = mapToLesson(lessonDto);
                lesson.setCourse(course);
                lessonRepository.save(lesson);
    }

    private  Lesson mapToLesson(LessonDto lessonDto) {
        return Lesson.builder()
                .id(lessonDto.getId())
                .title(lessonDto.getTitle())
                .videoPath(lessonDto.getVideoPath())
                .createdOn(lessonDto.getCreatedOn())
                .updatedOn(lessonDto.getUpdatedOn())
                .author(lessonDto.getAuthor())
                .duration(lessonDto.getDuration())
                .build();
    }

}
