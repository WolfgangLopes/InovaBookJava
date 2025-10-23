package com.inovabook.web.repository;

import com.inovabook.web.model.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Lesson, Long> {
}
