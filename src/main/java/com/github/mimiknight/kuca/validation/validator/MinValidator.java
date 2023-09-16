package com.github.mimiknight.kuca.validation.validator;

import com.github.mimiknight.kuca.validation.annotation.validation.Min;

/**
 * 最小值校验器
 *
 * @author victor2015yhm@gmail.com
 * @since 2023-06-07 20:13:23
 */
public class MinValidator implements ConstraintValidator<Min, Number> {
    private double min;
    private double delta;

    @Override
    public void initialize(Min constraintAnnotation) {
        this.min = constraintAnnotation.min();
        this.delta = constraintAnnotation.delta();
    }

    @Override
    public boolean isValid(Number value) {
        if (null == value) {
            return true;
        }
        double doubleValue = value.doubleValue();
        // value须大于等min
        return ((Double.compare(doubleValue, min) > 0) || (Math.abs(doubleValue - min) < delta));
    }
}
