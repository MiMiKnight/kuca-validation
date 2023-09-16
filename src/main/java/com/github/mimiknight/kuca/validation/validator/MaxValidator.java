package com.github.mimiknight.kuca.validation.validator;

import com.github.mimiknight.kuca.validation.annotation.validation.Max;

/**
 * 最大值校验器
 *
 * @author victor2015yhm@gmail.com
 * @since 2023-06-07 20:13:23
 */
public class MaxValidator implements ConstraintValidator<Max, Number> {
    private double max;
    private double delta;

    @Override
    public void initialize(Max constraintAnnotation) {
        this.max = constraintAnnotation.max();
        this.delta = constraintAnnotation.delta();
    }

    @Override
    public boolean isValid(Number value) {
        if (null == value) {
            return true;
        }
        double doubleValue = value.doubleValue();
        // value须小于等max
        return ((Double.compare(doubleValue, max) < 0) || (Math.abs(doubleValue - max) < delta));
    }
}
