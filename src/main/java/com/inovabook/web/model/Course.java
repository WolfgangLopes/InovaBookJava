package com.inovabook.web.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "courses")
@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
    private Integer duration;

    //@ManyToOne
    //private User instructor;

    private BigDecimal price;
    @Column(name = "thumbnail_path")
    private String thumbnailPath;

    //@OneToMany(mappedBy = "course")
    //private List<Lesson> lessons;

    //@OneToMany(mappedBy = "course")
    //private List<Enrollment> enrollments;

    private boolean published;
    //checar if @creationTimestamp
    private LocalDateTime publishedAt;
}
