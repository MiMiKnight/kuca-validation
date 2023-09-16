package com.github.mimiknight.kuca.validation.validator;

import com.github.mimiknight.kuca.validation.annotation.validation.Range;

/**
 * 元素个数注解校验器
 *
 * @author victor2015yhm@gmail.com
 * @since 2023-06-07 20:13:23
 */
public class RangeValidator implements ConstraintValidator<Range, Number> {

    private double min;
    private double max;
    private double delta;

    @Override
    public void initialize(Range constraintAnnotation) {
        this.min = constraintAnnotation.min();
        this.max = constraintAnnotation.max();
        this.delta = constraintAnnotation.delta();
    }

    @Override
    public boolean isValid(Number value) {
        if (null == value) {
            return true;
        }
        double doubleValue = value.doubleValue();
        // TODO 待完善
        return ((Double.compare(doubleValue, max) < 0) || (Math.abs(doubleValue - max) < delta));
    }
}
