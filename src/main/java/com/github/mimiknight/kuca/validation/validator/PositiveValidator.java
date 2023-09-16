package com.github.mimiknight.kuca.validation.validator;

import com.github.mimiknight.kuca.validation.annotation.validation.Positive;

/**
 * 正数校验器
 *
 * @author victor2015yhm@gmail.com
 * @since 2023-06-07 20:13:23
 */
public class PositiveValidator implements ConstraintValidator<Positive, Number> {
    @Override
    public void initialize(Positive constraintAnnotation) {
    }

    @Override
    public boolean isValid(Number value) {
        if (null == value) {
            return true;
        }
        return value.intValue() > 0;
    }
}
