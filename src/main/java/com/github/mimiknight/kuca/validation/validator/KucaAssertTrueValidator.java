package com.github.mimiknight.kuca.validation.validator;

import com.github.mimiknight.kuca.validation.annotation.validation.KucaAssertTrue;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 参数为true校验器
 *
 * @author victor2015yhm@gmail.com
 * @since 2023-06-07 20:13:23
 */
public class KucaAssertTrueValidator implements ConstraintValidator<KucaAssertTrue, Boolean> {

    @Override
    public void initialize(KucaAssertTrue constraintAnnotation) {
        // No initialization required
    }

    @Override
    public boolean isValid(Boolean value, ConstraintValidatorContext context) {
        if (null == value) {
            return true;
        }
        return Boolean.TRUE.equals(value);
    }
}
