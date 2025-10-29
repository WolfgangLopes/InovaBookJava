package com.inovabook.web.repository;

import com.inovabook.web.model.Lesson;
import com.inovabook.web.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonRepository extends JpaRepository<Lesson, Long> {
}
