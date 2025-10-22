package com.inovabook.web.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.util.UUID;

/*@Entity
@Table(name = enrollments)
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private User user;
    @ManyToOne
    private Course course;

    private LocalDateTime enrolledAt;

    @OneToMany(mappedBy = "enrollement")
    private List<Progress> progress;

    @Enumerated(EnumType.STRING)
    private EnrollmentStatus status;

}
*/