package com.inovabook.web.service.impl;

import com.inovabook.web.dto.CourseDto;
import com.inovabook.web.dto.LessonDto;
import com.inovabook.web.exception.CourseNotFoundException;
import com.inovabook.web.exception.LessonNotFoundException;
import com.inovabook.web.mapper.CourseMapper;
import com.inovabook.web.model.Course;
import com.inovabook.web.model.Lesson;
import com.inovabook.web.repository.CourseRepository;
import com.inovabook.web.repository.LessonRepository;
import com.inovabook.web.service.FileStorageService;
import com.inovabook.web.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import com.inovabook.web.mapper.LessonMapper;

import static com.inovabook.web.mapper.LessonMapper.mapToLessonDto;

@Service
public class LessonServiceImpl implements LessonService {
    private final FileStorageService fileStorageService;
    private final LessonRepository lessonRepository;
    private final CourseRepository courseRepository;

    @Autowired
    public LessonServiceImpl(LessonRepository lessonRepository, CourseRepository courseRepository, FileStorageService fileStorageService) {
        this.lessonRepository = lessonRepository;
        this.courseRepository = courseRepository;
        this.fileStorageService = fileStorageService;
    }

    @Override
    @Transactional
    public void createLesson(Long id,
                             LessonDto lessonDto,
                             MultipartFile videoFile) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException(id));
        if(videoFile != null && !videoFile.isEmpty()){
            String filename = fileStorageService.storeFile(videoFile, "video/lesson");
                    lessonDto.setVideoPath(filename);
        }
                Lesson lesson = LessonMapper.mapToLesson(lessonDto);
                lesson.setCourse(course);
                lessonRepository.save(lesson);
    }

    @Override
    @Transactional
    public void updateLesson(LessonDto lessonDto, MultipartFile file) {
        Lesson existing = lessonRepository.findById(lessonDto.getId())
                .orElseThrow(() -> new LessonNotFoundException(lessonDto.getId()));

        Lesson updatedData = LessonMapper.mapToLesson(lessonDto);

        existing.setTitle(updatedData.getTitle());
        existing.setAuthor(updatedData.getAuthor());
        existing.setDuration(updatedData.getDuration());

        if (file != null && !file.isEmpty()) {
            String newFileName = fileStorageService.replaceFile("video/", existing.getVideoPath(), file);
            existing.setVideoPath(newFileName);
        }
        lessonRepository.save(existing);
    }


    @Override
    public void deleteLesson(Long id) {lessonRepository.deleteById(id);}

    @Override
    public LessonDto findById(Long id) {
        Lesson lesson = this.lessonRepository.findById(id)
                .orElseThrow(()-> new LessonNotFoundException(id));
        return mapToLessonDto(lesson);
    }
}
