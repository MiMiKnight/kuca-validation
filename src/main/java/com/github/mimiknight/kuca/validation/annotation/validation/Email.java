package com.github.mimiknight.kuca.validation.annotation.validation;

import com.github.mimiknight.kuca.validation.annotation.Constraint;
import com.github.mimiknight.kuca.validation.validator.EmailValidator;
import com.github.mimiknight.kuca.validation.validator.PatternValidator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 电子邮箱格式校验注解
 *
 * @author victor2015yhm@gmail.com
 * @since 2023-06-07 20:05:34
 */
@Constraint(validatedBy = {EmailValidator.class})
@Documented
@Target(value = {ElementType.FIELD, ElementType.LOCAL_VARIABLE})
@Retention(value = RetentionPolicy.RUNTIME)
@Repeatable(value = Email.List.class)
public @interface Email {

    /**
     * 正则表达式
     *
     * @return {@link String}
     */
    String regex() default "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(.[a-zA-Z0-9_-]+)+$";

    /**
     * 消息
     *
     * @return {@link String}
     */
    String message() default "{com.github.mimiknight.kuca.validation.annotation.validation.Email.message}";

    /**
     * 错误码
     *
     * @return {@link String}
     */
    String errorCode() default "";

    /**
     * 分组
     *
     * @return {@link Class}<{@link ?}>{@link []}
     */
    Class<?>[] groups() default {};

    @Target(value = {ElementType.FIELD, ElementType.LOCAL_VARIABLE})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface List {
        Email[] value();
    }
}
