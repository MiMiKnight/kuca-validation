package com.github.mimiknight.kuca.validation.validator;

import com.github.mimiknight.kuca.validation.annotation.validation.KucaLength;
import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 字符串的字符个数校验注解
 *
 * @author victor2015yhm@gmail.com
 * @since 2023-06-07 20:13:23
 */
@Slf4j
public class KucaLengthValidator implements ConstraintValidator<KucaLength, String> {

    private int min;
    private int max;

    @Override
    public void initialize(KucaLength constraintAnnotation) {
        this.min = constraintAnnotation.min();
        this.max = constraintAnnotation.max();

        if (min > max) {
            log.warn("The min value must less than or equal max value.");
            throw new IllegalStateException("The min value must less than or equal max value.");
        }
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (null == value) {
            return true;
        }
        int size = value.length();
        return (size >= min && size <= max);
    }
}
