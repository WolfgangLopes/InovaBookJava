package com.inovabook.web.mapper;

import com.inovabook.web.dto.LessonDto;
import com.inovabook.web.model.Lesson;
import org.springframework.stereotype.Component;

@Component
public class LessonMapper {
    public static Lesson mapToLesson(LessonDto lessonDto) {
        return Lesson.builder()
                .title(lessonDto.getTitle())
                .videoPath(lessonDto.getVideoPath())
                .createdOn(lessonDto.getCreatedOn())
                .updatedOn(lessonDto.getUpdatedOn())
                .author(lessonDto.getAuthor())
                .duration(lessonDto.getDuration())
                .build();
    }

    public static LessonDto mapToLessonDto(Lesson lesson) {
        return LessonDto.builder()
                .id(lesson.getId())
                .title(lesson.getTitle())
                .videoPath(lesson.getVideoPath())
                .createdOn(lesson.getCreatedOn())
                .updatedOn(lesson.getUpdatedOn())
                .author(lesson.getAuthor())
                .duration(lesson.getDuration())
                .build();
    }
}
