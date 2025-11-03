package com.inovabook.web.service.impl;

import com.inovabook.web.dto.RegistrationDto;
import com.inovabook.web.dto.UserDto;
import com.inovabook.web.exception.EmailAlreadyUsedException;
import com.inovabook.web.exception.UserNotFoundException;
import com.inovabook.web.mapper.UserMapper;
import com.inovabook.web.model.Role;
import com.inovabook.web.model.User;
import com.inovabook.web.repository.RoleRepository;
import com.inovabook.web.repository.UserRepository;
import com.inovabook.web.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.UUID;

@Service
@RequiredArgsConstructor   // ← auto-injects all final fields
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;   // ← NOW INJECTED!

    @Transactional
    @Override
    public void save(RegistrationDto dto) {
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new EmailAlreadyUsedException(dto.getEmail());
        }

        Role userRole = roleRepository.findByName("USER")
                .orElseThrow(() -> new IllegalStateException(
                        "CRITICAL: Role 'USER' missing! Run: INSERT INTO roles (name) VALUES ('USER');"
                ));

        User user = User.builder()
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .roles(Collections.singleton(userRole))
                .build();

        userRepository.save(user);
    }

    @Override
    public UserDto findById(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        return UserMapper.mapToUserDto(user);
    }
}