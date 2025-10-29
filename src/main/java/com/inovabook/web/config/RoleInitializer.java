package com.inovabook.web.config;

import com.inovabook.web.model.Role;
import com.inovabook.web.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@RequiredArgsConstructor
class RoleInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;

    @Override
    public void run(String... args) {
        if (roleRepository.count() == 0) {
            roleRepository.save(new Role(null, "ADMIN", new ArrayList<>()));
            roleRepository.save(new Role(null, "USER", new ArrayList<>()));
        }
    }
}