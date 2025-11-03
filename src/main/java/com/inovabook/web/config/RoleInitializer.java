package com.inovabook.web.config;

import com.inovabook.web.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoleInitializer implements CommandLineRunner {

    private final UserRepository userRepository;

    @Override
    public void run(String... args) {
        userRepository.findByEmail("beo@beo.com").ifPresent(u -> {
            System.out.println("User roles: " + u.getRoles());
            System.out.println("Encoded password in DB: " + u.getPassword());

            // Optional: test if password matches what you think it is
            boolean matches = new BCryptPasswordEncoder().matches("beobeo", u.getPassword());
            System.out.println("Password matches 'beobeo'? " + matches);
        });
    }
}
