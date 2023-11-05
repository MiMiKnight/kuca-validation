package com.github.mimiknight.kuca.validation.validator;

import com.github.mimiknight.kuca.validation.annotation.validation.KucaFuture;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.ZonedDateTime;
import java.util.Date;

/**
 * 未来时间校验注解
 *
 * @author victor2015yhm@gmail.com
 * @since 2023-06-07 20:13:23
 */
public class KucaFutureValidator implements ConstraintValidator<KucaFuture, Object> {
    @Override
    public void initialize(KucaFuture constraintAnnotation) {
        // No initialization required
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (null == value) {
            return true;
        }
        // Date
        if (value instanceof Date) {
            Date now = Date.from(Instant.now());
            return now.before((Date) value);
        }
        // LocalTime
        if (value instanceof LocalTime) {
            LocalTime now = LocalTime.now();
            return now.isBefore((LocalTime) value);
        }
        // LocalDate
        if (value instanceof LocalDate) {
            LocalDate now = LocalDate.now();
            return now.isBefore((LocalDate) value);
        }
        // LocalDateTime
        if (value instanceof LocalDateTime) {
            LocalDateTime now = LocalDateTime.now();
            return now.isBefore((LocalDateTime) value);
        }
        // ZonedDateTime
        if (value instanceof ZonedDateTime) {
            ZonedDateTime now = ZonedDateTime.now();
            return now.isBefore((ZonedDateTime) value);
        }
        // OffsetTime
        if (value instanceof OffsetTime) {
            OffsetTime now = OffsetTime.now();
            return now.isBefore((OffsetTime) value);
        }
        // OffsetDateTime
        if (value instanceof OffsetDateTime) {
            OffsetDateTime now = OffsetDateTime.now();
            return now.isBefore((OffsetDateTime) value);
        }
        // 默认放通
        return true;
    }
}
