package com.github.mimiknight.kuca.validation.annotation.validation;

import com.github.mimiknight.kuca.validation.validator.KucaAssertFalseValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * false校验注解
 *
 * @author victor2015yhm@gmail.com
 * @since 2023-06-07 20:05:34
 */
@Documented
@Constraint(validatedBy = {KucaAssertFalseValidator.class})
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Repeatable(value = KucaAssertFalse.List.class)
public @interface KucaAssertFalse {

    /**
     * 消息
     */
    String message() default "{javax.validation.constraints.AssertFalse.message}";

    /**
     * 错误码
     */
    String errorCode() default "";

    /**
     * 分组
     */
    Class<?>[] groups() default {};

    /**
     * 载荷
     */
    Class<? extends Payload>[] payload() default {};

    @Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
    @Retention(RUNTIME)
    @Documented
    @interface List {
        KucaAssertFalse[] value();
    }
}
