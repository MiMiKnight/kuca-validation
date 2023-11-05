package com.github.mimiknight.kuca.validation.validator;

import com.github.mimiknight.kuca.validation.annotation.validation.KucaRange;
import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 元素个数注解校验器
 *
 * @author victor2015yhm@gmail.com
 * @since 2023-06-07 20:13:23
 */
@Slf4j
public class KucaRangeValidator implements ConstraintValidator<KucaRange, Number> {

    private double min;
    private double max;
    private double delta;

    @Override
    public void initialize(KucaRange constraintAnnotation) {
        this.min = constraintAnnotation.min();
        this.max = constraintAnnotation.max();
        this.delta = constraintAnnotation.delta();

        if (min > max) {
            log.warn("The min value must less than or equal max value.");
            throw new IllegalStateException("The min value must less than or equal max value.");
        }
    }

    @Override
    public boolean isValid(Number value, ConstraintValidatorContext context) {
        if (null == value) {
            return true;
        }
        double doubleValue = value.doubleValue();
        // value须小于等max,value须大于等min 即 value 介于[min,max]之间
        return ((Math.abs(doubleValue - min) <= delta) || (Double.compare(doubleValue, min) >= 0))
                && ((Math.abs(doubleValue - max) <= delta) || (Double.compare(doubleValue, max) <= 0));
    }
}
