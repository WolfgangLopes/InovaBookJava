package com.inovabook.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LessonDto {
    private Long id;
    private String title;
    private String videoPath;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String author;
    private Integer duration;
}
