package com.inovabook.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CourseSummaryDto {
    private Long id;
    private String title;
    private boolean enrolled;
}
