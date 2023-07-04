package cn.yhm.developer.kuca.validation.validator;

import cn.yhm.developer.kuca.validation.annotation.validation.ValidateNull;

/**
 * 参数为空校验器
 *
 * @author victor2015yhm@gmail.com
 * @since 2023-06-07 20:13:23
 */
public class NullValidator implements ConstraintValidator<ValidateNull, Object> {
    @Override
    public void initialize(ValidateNull constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Object value) {
        return value == null;
    }
}
