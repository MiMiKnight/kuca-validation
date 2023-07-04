package cn.yhm.developer.kuca.validation.validator;

import cn.yhm.developer.kuca.validation.annotation.validation.ValidateAssertFalse;

/**
 * 参数为false校验器
 *
 * @author victor2015yhm@gmail.com
 * @since 2023-06-07 20:13:23
 */
public class AssertFalseValidator implements ConstraintValidator<ValidateAssertFalse, Boolean> {
    @Override
    public void initialize(ValidateAssertFalse constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Boolean value) {
        if (null == value) {
            return true;
        }
        return Boolean.FALSE.equals(value);
    }
}
