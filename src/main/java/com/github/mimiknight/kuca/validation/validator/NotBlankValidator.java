package com.github.mimiknight.kuca.validation.validator;

import com.github.mimiknight.kuca.validation.annotation.validation.NotBlank;
import org.apache.commons.lang3.StringUtils;

/**
 * 参数非空校验器
 *
 * @author victor2015yhm@gmail.com
 * @since 2023-06-07 20:13:23
 */
public class NotBlankValidator implements ConstraintValidator<NotBlank, String> {
    @Override
    public void initialize(NotBlank constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value) {
        return StringUtils.isNotBlank(value);
    }
}
