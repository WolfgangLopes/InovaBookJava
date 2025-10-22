/*package com.inovabook.web.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.GenerationType;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User {
    @Id
    //@GeneratedValue(strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    private String username;
    private String password;
    private String email;

    private String firstName;
    private String lastName;

    @Enumerated(EnumType.String)
    private UserRole role;

    @OneToMany(mappedBy = "user")
    private List<Enrollment> enrollments;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean enabled;
}*/
