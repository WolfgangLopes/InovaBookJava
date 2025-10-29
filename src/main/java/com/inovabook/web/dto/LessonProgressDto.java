package com.inovabook.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LessonProgressDto {
    private Long lessonId;
    private String title;
    private boolean completed;
}