package com.inovabook.web.service.impl;

import com.inovabook.web.dto.RegistrationDto;
import com.inovabook.web.dto.UserDto;
import com.inovabook.web.exception.EmailAlreadyUsedException;
import com.inovabook.web.exception.UsernameAlreadyUsedException;
import com.inovabook.web.model.Role;
import com.inovabook.web.model.User;
import com.inovabook.web.repository.RoleRepository;
import com.inovabook.web.repository.UserRepository;
import com.inovabook.web.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Arrays;



@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    //private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(RoleRepository roleRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        //this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    @Override
    public void save(RegistrationDto registrationDto) {
        if (userRepository.existsByEmail(registrationDto.getEmail())) {
            throw new EmailAlreadyUsedException(registrationDto.getEmail());
        }

        // 2️⃣ (optional) check for duplicate username
        if (userRepository.existsByUsername(registrationDto.getUsername())) {
            throw new UsernameAlreadyUsedException(registrationDto.getUsername());
        }
        User user = new User();
        user.setUsername(registrationDto.getUsername());
        user.setEmail(registrationDto.getEmail());
        user.setPassword(registrationDto.getPassword());
        Role role = roleRepository.findByName("USER");
        user.setRoles(Arrays.asList(role));
        userRepository.save(user);
    }
}
