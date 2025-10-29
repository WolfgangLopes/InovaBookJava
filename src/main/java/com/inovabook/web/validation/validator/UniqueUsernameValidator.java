package com.inovabook.web.validation.validator;

import com.inovabook.web.repository.UserRepository;
import com.inovabook.web.validation.annotation.UniqueUsername;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

    private final UserRepository userRepository;

    public UniqueUsernameValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext ctx) {
        if (username == null || username.isBlank()) {
            return true; // @NotBlank will catch emptiness
        }
        //Return if false (Does not exist)
        return !userRepository.existsByUsername(username);
    }
}