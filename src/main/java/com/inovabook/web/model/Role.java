package com.inovabook.web.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
//@ToString(onlyExplicitlyIncluded = true)
public class Role {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    @NonNull
    @ToString.Include
    private final String name;

    /*@ManyToMany(mappedBy = "roles")
    @Setter(AccessLevel.PRIVATE)
    private Set<User> users = new HashSet<>();*/
}