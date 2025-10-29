package com.inovabook.web.repository;

import com.inovabook.web.model.Lesson;
import com.inovabook.web.model.LessonProgress;
import com.inovabook.web.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LessonProgressRepository extends JpaRepository<LessonProgress, Long> {
    boolean existsByUserAndLesson(User user, Lesson lesson);
    Optional<LessonProgress> findByUserAndLesson(User user, Lesson lesson);
    boolean existsByUserAndLessonAndCompleted(User user, Lesson lesson, boolean completed);
    List<LessonProgress> findByUserAndLessonIn(User user, List<Lesson> lessons);
}