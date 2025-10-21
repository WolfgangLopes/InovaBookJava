package com.inovabook.web.dto;

//import com.inovabook.web.model.User;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class CourseDto {
    private Long id;
    private String title;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String thumbnailPath;
    private Integer duration;

    //private User instructor;
    private BigDecimal price;
    private boolean published;
    private LocalDateTime publishedAt;
}
