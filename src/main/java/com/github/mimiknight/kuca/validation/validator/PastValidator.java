package com.github.mimiknight.kuca.validation.validator;

import com.github.mimiknight.kuca.validation.annotation.validation.Past;

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
public class PastValidator implements ConstraintValidator<Past, Object> {
    @Override
    public void initialize(Past constraintAnnotation) {
    }

    @Override
    public boolean isValid(Object value) {
        if (null == value) {
            return true;
        }
        // Date
        if (value instanceof Date) {
            Date now = Date.from(Instant.now());
            return now.after((Date) value);
        }
        // LocalTime
        if (value instanceof LocalTime) {
            LocalTime now = LocalTime.now();
            return now.isAfter((LocalTime) value);
        }
        // LocalDate
        if (value instanceof LocalDate) {
            LocalDate now = LocalDate.now();
            return now.isAfter((LocalDate) value);
        }
        // LocalDateTime
        if (value instanceof LocalDateTime) {
            LocalDateTime now = LocalDateTime.now();
            return now.isAfter((LocalDateTime) value);
        }
        // ZonedDateTime
        if (value instanceof ZonedDateTime) {
            ZonedDateTime now = ZonedDateTime.now();
            return now.isAfter((ZonedDateTime) value);
        }
        // OffsetDateTime
        if (value instanceof OffsetDateTime) {
            OffsetDateTime now = OffsetDateTime.now();
            return now.isAfter((OffsetDateTime) value);
        }
        // OffsetTime
        if (value instanceof OffsetTime) {
            OffsetTime now = OffsetTime.now();
            return now.isAfter((OffsetTime) value);
        }
        // 默认放通
        return true;
    }
}
