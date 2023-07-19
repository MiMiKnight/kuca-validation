package com.github.mimiknight.kuca.validation.validator;

import com.github.mimiknight.kuca.validation.annotation.validation.ValidateLength;

/**
 * 字符串的字符个数校验注解
 *
 * @author victor2015yhm@gmail.com
 * @since 2023-06-07 20:13:23
 */
public class LengthValidator implements ConstraintValidator<ValidateLength, String> {

    private int min;
    private int max;

    @Override
    public void initialize(ValidateLength constraintAnnotation) {
        this.min = constraintAnnotation.min();
        this.max = constraintAnnotation.max();
    }

    @Override
    public boolean isValid(String value) {
        if (null == value) {
            return true;
        }
        int size = value.length();
        return (size >= min && size <= max);
    }
}
