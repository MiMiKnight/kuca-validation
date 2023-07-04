package cn.yhm.developer.kuca.validation.validator;

import cn.yhm.developer.kuca.validation.annotation.validation.ValidatePattern;

/**
 * 正则校验注解校验器
 *
 * @author victor2015yhm@gmail.com
 * @since 2023-06-07 20:13:23
 */
public class PatternValidator implements ConstraintValidator<ValidatePattern, String> {

    private String regex;

    @Override
    public void initialize(ValidatePattern constraintAnnotation) {
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
