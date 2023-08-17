package com.github.mimiknight.kuca.validation.validator;

import com.github.mimiknight.kuca.validation.annotation.validation.ValidatePast;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
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
        // Date
        if (value instanceof Date date) {
            Date now = Date.from(Instant.now());
            return now.after(date);
        }
        // LocalTime
        if (value instanceof LocalTime date) {
            LocalTime now = LocalTime.now();
            return now.isAfter(date);
        }
        // LocalDate
        if (value instanceof LocalDate date) {
            LocalDate now = LocalDate.now();
            return now.isAfter(date);
        }
        // LocalDateTime
        if (value instanceof LocalDateTime date) {
            LocalDateTime now = LocalDateTime.now();
            return now.isAfter(date);
        }
        // ZonedDateTime
        if (value instanceof ZonedDateTime date) {
            ZonedDateTime now = ZonedDateTime.now();
            return now.isAfter(date);
        }
        // OffsetDateTime
        if (value instanceof OffsetDateTime date) {
            OffsetDateTime now = OffsetDateTime.now();
            return now.isAfter(date);
        }
        // OffsetTime
        if (value instanceof OffsetTime date) {
            OffsetTime now = OffsetTime.now();
            return now.isAfter(date);
        }
        // 默认放通
        return true;
    }
}
