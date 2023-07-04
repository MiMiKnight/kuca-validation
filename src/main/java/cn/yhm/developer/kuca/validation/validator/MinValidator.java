package cn.yhm.developer.kuca.validation.validator;

import cn.yhm.developer.kuca.validation.annotation.validation.ValidateMin;

/**
 * 最小值校验器
 *
 * @author victor2015yhm@gmail.com
 * @since 2023-06-07 20:13:23
 */
public class MinValidator implements ConstraintValidator<ValidateMin, Number> {
    private double min;
    private double delta;

    @Override
    public void initialize(ValidateMin constraintAnnotation) {
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
