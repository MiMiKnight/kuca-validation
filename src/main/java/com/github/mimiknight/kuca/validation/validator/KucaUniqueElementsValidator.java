package com.github.mimiknight.kuca.validation.validator;

import com.github.mimiknight.kuca.validation.annotation.validation.KucaUniqueElements;
import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 元素唯一性校验器
 *
 * @author victor2015yhm@gmail.com
 * @since 2023-06-07 20:13:23
 */
@Slf4j
public class KucaUniqueElementsValidator implements ConstraintValidator<KucaUniqueElements, Object> {

    private boolean caseSensitive;

    @Override
    public void initialize(KucaUniqueElements constraintAnnotation) {
        this.caseSensitive = constraintAnnotation.caseSensitive();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (null == value) {
            return true;
        }
        // TODO 待完善
        // 默认放通
        return true;
    }

}
