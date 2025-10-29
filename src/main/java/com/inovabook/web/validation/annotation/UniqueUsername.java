package com.inovabook.web.validation.annotation;

import com.inovabook.web.validation.validator.UniqueUsernameValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueUsernameValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueUsername {
    String message() default "Nome de usuário já cadastrado";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}