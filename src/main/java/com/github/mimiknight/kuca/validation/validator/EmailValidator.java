package com.github.mimiknight.kuca.validation.validator;

import com.github.mimiknight.kuca.validation.annotation.validation.ValidateEmail;

/**
 * 电子邮箱格式校验注解校验器
 *
 * @author victor2015yhm@gmail.com
 * @since 2023-06-07 20:13:23
 */
public class EmailValidator implements ConstraintValidator<ValidateEmail, String> {

    private String regex;

    @Override
    public void initialize(ValidateEmail constraintAnnotation) {
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
