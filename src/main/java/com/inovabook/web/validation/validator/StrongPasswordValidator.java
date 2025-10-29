package com.inovabook.web.validation.validator;

import com.inovabook.web.validation.annotation.StrongPassword;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

/**
 * Checks that a password fulfills a basic “strong password” policy:
 *
 * <ul>
 *   <li>minimum length (default 8)</li>
 *   <li>at least one upper‑case letter</li>
 *   <li>at least one lower‑case letter</li>
 *   <li>at least one digit</li>
 *   <li>at least one special character (e.g. @$!%*?&amp;)</li>
 * </ul>
 *
 * The policy is expressed as a single regular expression.
 *
 * If you need a more sophisticated estimator (zxcvbn, entropy calculation,
 * dictionary checks, etc.) replace the {@code isValid} method accordingly.
 */
public class StrongPasswordValidator
        implements ConstraintValidator<StrongPassword, CharSequence> {

    private static final String DEFAULT_PATTERN =
            "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";

    private Pattern pattern;

    @Override
    public void initialize(StrongPassword constraintAnnotation) {
        this.pattern = Pattern.compile(DEFAULT_PATTERN);
    }

    @Override
    public boolean isValid(CharSequence value, ConstraintValidatorContext ctx) {
        if (value == null || value.length() == 0) {
            return true;
        }
        boolean matches = pattern.matcher(value).matches();
        if (!matches) {
            // ctx.disableDefaultConstraintViolation();
            ctx.buildConstraintViolationWithTemplate("{password.weak}").addConstraintViolation();
        }
        return matches;
    }
}