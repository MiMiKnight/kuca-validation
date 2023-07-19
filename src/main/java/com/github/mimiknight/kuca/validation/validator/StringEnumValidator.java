package com.github.mimiknight.kuca.validation.validator;

import com.github.mimiknight.kuca.validation.annotation.validation.ValidateStringEnum;

import java.util.Arrays;

/**
 * 字符串类型参数枚举校验器
 *
 * @author victor2015yhm@gmail.com
 * @since 2023-06-07 20:13:23
 */
public class StringEnumValidator implements ConstraintValidator<ValidateStringEnum, String> {

    private String[] enumValueArray;

    @Override
    public void initialize(ValidateStringEnum constraintAnnotation) {
        this.enumValueArray = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(String value) {
        if (null == value || null == enumValueArray || enumValueArray.length < 1) {
            return true;
        }
        // 枚举值去重
        enumValueArray = Arrays.stream(enumValueArray).distinct().toArray(String[]::new);
        return Arrays.asList(enumValueArray).contains(value);
    }

}
