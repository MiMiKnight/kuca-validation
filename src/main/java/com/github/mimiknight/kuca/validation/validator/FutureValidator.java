package com.github.mimiknight.kuca.validation.validator;

import com.github.mimiknight.kuca.validation.annotation.validation.ValidateFuture;

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
        // Date
        if (value instanceof Date date) {
            Date now = Date.from(Instant.now());
            return now.before(date);
        }
        // LocalTime
        if (value instanceof LocalTime date) {
            LocalTime now = LocalTime.now();
            return now.isBefore(date);
        }
        // LocalDate
        if (value instanceof LocalDate date) {
            LocalDate now = LocalDate.now();
            return now.isBefore(date);
        }
        // LocalDateTime
        if (value instanceof LocalDateTime date) {
            LocalDateTime now = LocalDateTime.now();
            return now.isBefore(date);
        }
        // ZonedDateTime
        if (value instanceof ZonedDateTime date) {
            ZonedDateTime now = ZonedDateTime.now();
            return now.isBefore(date);
        }
        // OffsetDateTime
        if (value instanceof OffsetDateTime date) {
            OffsetDateTime now = OffsetDateTime.now();
            return now.isBefore(date);
        }
        // OffsetTime
        if (value instanceof OffsetTime date) {
            OffsetTime now = OffsetTime.now();
            return now.isBefore(date);
        }
        // 默认放通
        return true;
    }
}
