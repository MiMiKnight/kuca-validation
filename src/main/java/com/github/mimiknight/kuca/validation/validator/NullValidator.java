package com.github.mimiknight.kuca.validation.validator;

import com.github.mimiknight.kuca.validation.annotation.validation.Null;

/**
 * 参数为空校验器
 *
 * @author victor2015yhm@gmail.com
 * @since 2023-06-07 20:13:23
 */
public class NullValidator implements ConstraintValidator<Null, Object> {
    @Override
    public void initialize(Null constraintAnnotation) {
    }

    @Override
    public boolean isValid(Object value) {
        return value == null;
    }
}
