package com.inovabook.web.validation.validator;

import com.inovabook.web.repository.UserRepository;
import com.inovabook.web.validation.annotation.UniqueEmail;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;


@Component
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    private final UserRepository userRepository;

    public UniqueEmailValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext ctx) {
        if (email == null || email.isBlank()) {
            return true; // @NotBlank will catch emptiness
        }
        //Return if false (Does not exist)
        return !userRepository.existsByEmail(email);
    }
}