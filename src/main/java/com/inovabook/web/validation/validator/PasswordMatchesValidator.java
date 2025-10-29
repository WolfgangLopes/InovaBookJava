package com.inovabook.web.validation.validator;

import com.inovabook.web.validation.annotation.PasswordMatches;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.beans.Introspector;
import java.lang.reflect.Method;

public class PasswordMatchesValidator
        implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext ctx) {
        if (obj == null) {
            return true;
        }
        try {
            Method getPassword = obj.getClass().getMethod("getPassword");
            Method getConfirm = obj.getClass().getMethod("getConfirmPassword");

            Object pwdObj = getPassword.invoke(obj);
            Object confirmObj = getConfirm.invoke(obj);

            if (pwdObj == null && confirmObj == null) {
                return true;
            }

            boolean matches = (pwdObj != null && pwdObj.equals(confirmObj));

            if (!matches) {
                ctx.disableDefaultConstraintViolation();
                ctx.buildConstraintViolationWithTemplate(
                                ctx.getDefaultConstraintMessageTemplate())
                        .addPropertyNode("confirmPassword")
                        .addConstraintViolation();
            }

            return matches;
        } catch (Exception e) {
            return false;
        }
    }
}