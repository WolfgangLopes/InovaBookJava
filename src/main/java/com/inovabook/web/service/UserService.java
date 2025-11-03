package com.inovabook.web.service;

import com.inovabook.web.dto.RegistrationDto;
import com.inovabook.web.dto.UserDto;
import java.util.UUID;

public interface UserService {
    void save(RegistrationDto registrationDto);

    UserDto findById(UUID id);
}
