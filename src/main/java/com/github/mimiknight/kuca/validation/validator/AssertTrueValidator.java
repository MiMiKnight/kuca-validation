package com.github.mimiknight.kuca.validation.validator;

import com.github.mimiknight.kuca.validation.annotation.validation.ValidateAssertTrue;

/**
 * 参数为true校验器
 *
 * @author victor2015yhm@gmail.com
 * @since 2023-06-07 20:13:23
 */
public class AssertTrueValidator implements ConstraintValidator<ValidateAssertTrue, Boolean> {
    @Override
    public void initialize(ValidateAssertTrue constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Boolean value) {
        if (null == value) {
            return true;
        }
        return Boolean.TRUE.equals(value);
    }
}
