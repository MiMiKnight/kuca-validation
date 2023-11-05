package com.github.mimiknight.kuca.validation.validator;

import com.github.mimiknight.kuca.validation.annotation.validation.KucaPositive;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 正数校验器
 *
 * @author victor2015yhm@gmail.com
 * @since 2023-06-07 20:13:23
 */
public class KucaPositiveValidator implements ConstraintValidator<KucaPositive, Number> {
    @Override
    public void initialize(KucaPositive constraintAnnotation) {
        // No initialization required
    }

    @Override
    public boolean isValid(Number value, ConstraintValidatorContext context) {
        if (null == value) {
            return true;
        }
        return value.doubleValue() > 0;
    }
}
