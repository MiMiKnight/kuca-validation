package com.github.mimiknight.kuca.validation.annotation.validation;

import com.github.mimiknight.kuca.validation.validator.KucaSizeValidator;

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
 * 元素个数校验注解
 * <p>
 * 字符串的字符个数 {@link String}
 * <p>
 * 数组的元素个数
 * <p>
 * 单列集合的元素个数 {@link java.util.Collection}
 * <p>
 * 双列集合的元素个数 {@link java.util.Map}
 *
 * @author victor2015yhm@gmail.com
 * @since 2023-06-07 20:05:34
 */
@Documented
@Constraint(validatedBy = {KucaSizeValidator.class})
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Repeatable(value = KucaSize.List.class)
public @interface KucaSize {

    /**
     * 最小值
     */
    int min() default 0;

    /**
     * 最大值
     */
    int max() default Integer.MAX_VALUE;

    /**
     * 消息
     */
    String message() default "{javax.validation.constraints.Size.message}";

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
        KucaSize[] value();
    }
}
