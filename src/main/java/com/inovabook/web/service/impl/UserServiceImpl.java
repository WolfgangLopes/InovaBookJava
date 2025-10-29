package com.inovabook.web.service.impl;

import com.inovabook.web.dto.RegistrationDto;
import com.inovabook.web.model.Role;
import com.inovabook.web.model.User;
import com.inovabook.web.repository.RoleRepository;
import com.inovabook.web.repository.UserRepository;
import com.inovabook.web.service.UserService;

public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;

    public UserServiceImpl(RoleRepository roleRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void save(RegistrationDto registrationDto) {
        User user = new User();
        user.setUsername(registrationDto.getUsername());
        user.setEmail(registrationDto.getEmail());
        user.setPassword(registrationDto.getPassword());
        Role role = roleRepository.findByName("USER");
        User.setRoles(Arrays.asList(role));
    }
}
