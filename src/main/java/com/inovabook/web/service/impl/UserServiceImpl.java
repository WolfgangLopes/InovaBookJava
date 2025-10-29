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
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.UUID;

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
        if (userRepository.existsByEmail(registrationDto.getEmail())) {
            throw new EmailAlreadyUsedException(registrationDto.getEmail());
        }
        User user = new User();
        user.setUsername(registrationDto.getEmail());
        user.setEmail(registrationDto.getEmail());
        user.setPassword(registrationDto.getPassword());
        Role role = roleRepository.findByName("USER");
        user.setRoles(Arrays.asList(role));
        userRepository.save(user);
    }

    @Override
    public UserDto findById(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        return UserMapper.mapToUserDto(user);
    }
}
