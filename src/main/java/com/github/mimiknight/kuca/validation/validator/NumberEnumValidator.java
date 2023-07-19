package com.github.mimiknight.kuca.validation.validator;

import com.github.mimiknight.kuca.validation.annotation.validation.ValidateNumberEnum;

import java.util.Arrays;

/**
 * 数字类型参数枚举校验器
 *
 * @author victor2015yhm@gmail.com
 * @since 2023-06-07 20:13:23
 */
public class NumberEnumValidator implements ConstraintValidator<ValidateNumberEnum, Number> {

    private double[] enumValueArray;
    private double delta;

    @Override
    public void initialize(ValidateNumberEnum constraintAnnotation) {
        this.enumValueArray = constraintAnnotation.value();
        this.delta = constraintAnnotation.delta();
    }

    @Override
    public boolean isValid(Number value) {
        if (null == value || null == enumValueArray || enumValueArray.length < 1) {
            return true;
        }
        // 枚举值去重
        enumValueArray = Arrays.stream(enumValueArray).distinct().toArray();
        return Arrays.stream(enumValueArray).anyMatch(en -> Math.abs(value.doubleValue() - en) <= delta);
    }
}
