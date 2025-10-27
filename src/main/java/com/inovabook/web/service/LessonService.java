package com.inovabook.web.service;

import com.inovabook.web.dto.LessonDto;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface LessonService {
    void createLesson(Long id, LessonDto lessonDto, MultipartFile file);

    LessonDto findById(Long lessonId);

}
