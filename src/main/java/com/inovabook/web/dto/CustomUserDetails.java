package com.inovabook.web.dto;

import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Getter
public class CustomUserDetails extends org.springframework.security.core.userdetails.User {
    private final com.inovabook.web.model.User user;

    public CustomUserDetails(com.inovabook.web.model.User user) {
        super(user.getEmail(),
                user.getPassword(),
                user.getRoles().stream()
                        .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName()))
                        .toList());
        this.user = user;
    }

}
