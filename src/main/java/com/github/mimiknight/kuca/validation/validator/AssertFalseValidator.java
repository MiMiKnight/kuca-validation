package com.github.mimiknight.kuca.validation.validator;

import com.github.mimiknight.kuca.validation.annotation.validation.AssertFalse;

/**
 * 参数为false校验器
 *
 * @author victor2015yhm@gmail.com
 * @since 2023-06-07 20:13:23
 */
public class AssertFalseValidator implements ConstraintValidator<AssertFalse, Boolean> {
    @Override
    public void initialize(AssertFalse constraintAnnotation) {
    }

    @Override
    public boolean isValid(Boolean value) {
        if (null == value) {
            return true;
        }
        return Boolean.FALSE.equals(value);
    }
}
