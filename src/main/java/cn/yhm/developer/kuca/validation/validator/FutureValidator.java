package cn.yhm.developer.kuca.validation.validator;

import cn.yhm.developer.kuca.validation.annotation.validation.ValidateFuture;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.Date;

/**
 * 未来时间校验注解
 *
 * @author victor2015yhm@gmail.com
 * @since 2023-06-07 20:13:23
 */
public class FutureValidator implements ConstraintValidator<ValidateFuture, Object> {
    @Override
    public void initialize(ValidateFuture constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Object value) {
        if (null == value) {
            return true;
        }
        if (value instanceof ZonedDateTime) {
            ZonedDateTime now = ZonedDateTime.now();
            return now.isBefore((ZonedDateTime) value);
        }
        if (value instanceof Date) {
            Date now = Date.from(Instant.now());
            return now.before((Date) value);
        }
        return true;
    }
}
