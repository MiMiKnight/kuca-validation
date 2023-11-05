package com.github.mimiknight.kuca.validation.validator;

import com.github.mimiknight.kuca.validation.annotation.validation.KucaAssertFalse;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 参数为false校验器
 *
 * @author victor2015yhm@gmail.com
 * @since 2023-06-07 20:13:23
 */
public class KucaAssertFalseValidator implements ConstraintValidator<KucaAssertFalse, Boolean> {

    @Override
    public void initialize(KucaAssertFalse constraintAnnotation) {
        // No initialization required
    }

    @Override
    public boolean isValid(Boolean value, ConstraintValidatorContext context) {
        if (null == value) {
            return true;
        }
        return Boolean.FALSE.equals(value);
    }


}
