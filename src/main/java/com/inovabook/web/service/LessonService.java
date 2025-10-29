package com.inovabook.web.service;

import com.inovabook.web.dto.LessonDto;
import com.inovabook.web.dto.LessonProgressDto;
import com.inovabook.web.model.User;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface LessonService {
    void createLesson(Long id, LessonDto lessonDto, MultipartFile file);

    void updateLesson(LessonDto lessonDto, MultipartFile file);

    void deleteLesson(Long id);

    LessonDto findById(Long id);

    List<LessonProgressDto> getLessonProgressForCourse(User user, Long id);

    void markLessonAsSeen(User user, Long id);


}
