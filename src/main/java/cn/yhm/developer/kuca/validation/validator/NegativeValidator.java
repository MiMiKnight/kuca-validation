package cn.yhm.developer.kuca.validation.validator;

import cn.yhm.developer.kuca.validation.annotation.validation.ValidatePositive;

/**
 * 正数校验器
 *
 * @author victor2015yhm@gmail.com
 * @since 2023-06-07 20:13:23
 */
public class NegativeValidator implements ConstraintValidator<ValidatePositive, Number> {
    @Override
    public void initialize(ValidatePositive constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Number value) {
        if (null == value) {
            return true;
        }
        return value.intValue() < 0;
    }
}
