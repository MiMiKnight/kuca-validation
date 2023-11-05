package com.github.mimiknight.kuca.validation.validator;

import com.github.mimiknight.kuca.validation.annotation.validation.KucaMax;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 最大值校验器
 *
 * @author victor2015yhm@gmail.com
 * @since 2023-06-07 20:13:23
 */
public class KucaMaxValidator implements ConstraintValidator<KucaMax, Number> {
    private double max;
    private double delta;

    @Override
    public void initialize(KucaMax constraintAnnotation) {
        this.max = constraintAnnotation.max();
        this.delta = constraintAnnotation.delta();
    }

    @Override
    public boolean isValid(Number value, ConstraintValidatorContext context) {
        if (null == value) {
            return true;
        }
        double doubleValue = value.doubleValue();
        // value须小于等max
        return ((Math.abs(doubleValue - max) <= delta) || (Double.compare(doubleValue, max) <= 0));
    }
}
