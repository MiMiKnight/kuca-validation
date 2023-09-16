package com.github.mimiknight.kuca.validation.validator;

import com.github.mimiknight.kuca.validation.annotation.validation.Pattern;

/**
 * 正则校验注解校验器
 *
 * @author victor2015yhm@gmail.com
 * @since 2023-06-07 20:13:23
 */
public class PatternValidator implements ConstraintValidator<Pattern, String> {

    private String regex;

    @Override
    public void initialize(Pattern constraintAnnotation) {
        this.regex = constraintAnnotation.regex();
    }

    @Override
    public boolean isValid(String value) {
        if (null == value) {
            return true;
        }
        return value.matches(regex);
    }
}
