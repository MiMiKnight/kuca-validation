package com.github.mimiknight.kuca.validation.annotation.validation;

import com.github.mimiknight.kuca.validation.validator.KucaEmptyValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Null;
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
 * 参数为空校验注解
 *
 * <p>
 * 字符串
 * <p>
 * 数组
 * <p>
 * 单列集合
 * <p>
 * 双列集合
 *
 * @author victor2015yhm@gmail.com
 * @since 2023-06-07 20:05:34
 */
@Null
@Documented
@Constraint(validatedBy = {KucaEmptyValidator.class})
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Repeatable(value = KucaEmpty.List.class)
public @interface KucaEmpty {

    /**
     * 消息
     */
    String message() default "{com.github.mimiknight.kuca.validation.annotation.validation.KucaEmpty.message}";

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
        KucaEmpty[] value();
    }
}
