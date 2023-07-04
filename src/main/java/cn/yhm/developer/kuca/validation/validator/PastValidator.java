package cn.yhm.developer.kuca.validation.validator;

import cn.yhm.developer.kuca.validation.annotation.validation.ValidatePast;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.Date;

/**
 * 过去时间校验注解
 *
 * @author victor2015yhm@gmail.com
 * @since 2023-06-07 20:13:23
 */
public class PastValidator implements ConstraintValidator<ValidatePast, Object> {
    @Override
    public void initialize(ValidatePast constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Object value) {
        if (null == value) {
            return true;
        }
        if (value instanceof ZonedDateTime) {
            ZonedDateTime now = ZonedDateTime.now();
            return now.isAfter((ZonedDateTime) value);
        }
        if (value instanceof Date) {
            Date now = Date.from(Instant.now());
            return now.after((Date) value);
        }
        return true;
    }
}
