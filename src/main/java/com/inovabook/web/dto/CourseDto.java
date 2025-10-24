package com.inovabook.web.dto;

//import com.inovabook.web.model.User;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class CourseDto {
    private Long id;
    @NotEmpty(message="{course.title.notnull}")
    private String title;
    @NotEmpty(message="{course.description.notnull}")
    private String description;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
    private String thumbnailPath;
    @Min(value=1, message="{course.duration.min}")
    private Integer duration;

    //private User instructor;
    private BigDecimal price;
    private boolean published;
    private LocalDateTime publishedOn;
}
