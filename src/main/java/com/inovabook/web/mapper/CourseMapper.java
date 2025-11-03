package com.inovabook.web.mapper;

import com.inovabook.web.dto.CourseDto;
import com.inovabook.web.model.Course;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

import static com.inovabook.web.mapper.LessonMapper.mapToLessonDto;

@Component
public class CourseMapper {
    public static Course mapToCourse(CourseDto course) {
        return Course.builder()
                .id(course.getId())
                .title(course.getTitle())
                .description(course.getDescription())
                .price(course.getPrice())
                .duration(course.getDuration())
                .thumbnailPath(course.getThumbnailPath())
                .published(course.isPublished())
                .build();
    }

    public static CourseDto mapToCourseDto (Course course) {
        return CourseDto.builder()
                .id(course.getId())
                .title(course.getTitle())
                .description(course.getDescription())
                .createdOn(course.getCreatedOn())
                .updatedOn(course.getUpdatedOn())
                .price(course.getPrice())
                .thumbnailPath(course.getThumbnailPath())
                .published(course.isPublished())
                .publishedOn(course.getPublishedOn())
                .duration(course.getDuration())
                .lessons(course.getLessons().stream().map(LessonMapper::mapToLessonDto).collect(Collectors.toList()))
                .build();
    }
}
