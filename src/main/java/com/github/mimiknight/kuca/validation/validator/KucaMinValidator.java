package com.github.mimiknight.kuca.validation.validator;

import com.github.mimiknight.kuca.validation.annotation.validation.KucaMin;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 最小值校验器
 *
 * @author victor2015yhm@gmail.com
 * @since 2023-06-07 20:13:23
 */
public class KucaMinValidator implements ConstraintValidator<KucaMin, Number> {
    private double min;
    private double delta;

    @Override
    public void initialize(KucaMin constraintAnnotation) {
        this.min = constraintAnnotation.min();
        this.delta = constraintAnnotation.delta();
    }

    @Override
    public boolean isValid(Number value, ConstraintValidatorContext context) {
        if (null == value) {
            return true;
        }
        double doubleValue = value.doubleValue();
        // value须大于等min
        return ((Math.abs(doubleValue - min) <= delta) || (Double.compare(doubleValue, min) >= 0));
    }

}
