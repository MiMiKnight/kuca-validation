package cn.yhm.developer.kuca.validation.validator;

import cn.yhm.developer.kuca.validation.annotation.validation.ValidateNotBlank;
import org.apache.commons.lang3.StringUtils;

/**
 * 参数非空校验器
 *
 * @author victor2015yhm@gmail.com
 * @since 2023-06-07 20:13:23
 */
public class NotBlankValidator implements ConstraintValidator<ValidateNotBlank, String> {
    @Override
    public void initialize(ValidateNotBlank constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value) {
        if (null == value) {
            return true;
        }
        return StringUtils.isNotBlank(value);
    }
}
