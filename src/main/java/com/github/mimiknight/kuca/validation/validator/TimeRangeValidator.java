package com.github.mimiknight.kuca.validation.validator;

import com.github.mimiknight.kuca.validation.annotation.validation.TimeRange;

/**
 * 元素个数注解校验器
 *
 * @author victor2015yhm@gmail.com
 * @since 2023-06-07 20:13:23
 */
public class TimeRangeValidator implements ConstraintValidator<TimeRange, Object> {

    private String min;
    private String max;

    @Override
    public void initialize(TimeRange constraintAnnotation) {
        String min = constraintAnnotation.min();
        String max = constraintAnnotation.max();
    }

    @Override
    public boolean isValid(Object value) {
        if (null == value) {
            return true;
        }
        // TODO 待完善
        return true;
    }
}
