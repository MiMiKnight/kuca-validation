package com.github.mimiknight.kuca.validation.validator;

import com.github.mimiknight.kuca.validation.annotation.validation.AssertTrue;

/**
 * 参数为true校验器
 *
 * @author victor2015yhm@gmail.com
 * @since 2023-06-07 20:13:23
 */
public class AssertTrueValidator implements ConstraintValidator<AssertTrue, Boolean> {
    @Override
    public void initialize(AssertTrue constraintAnnotation) {
    }

    @Override
    public boolean isValid(Boolean value) {
        if (null == value) {
            return true;
        }
        return Boolean.TRUE.equals(value);
    }
}
