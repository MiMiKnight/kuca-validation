package cn.yhm.developer.kuca.validation.annotation.validation;

import cn.yhm.developer.kuca.validation.annotation.Constraint;
import cn.yhm.developer.kuca.validation.validator.PatternValidator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 正则校验注解
 *
 * @author victor2015yhm@gmail.com
 * @since 2023-06-07 20:05:34
 */
@Constraint(validatedBy = {PatternValidator.class})
@Documented
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = {ElementType.FIELD})
public @interface ValidatePattern {

    /**
     * 正则表达式
     *
     * @return {@link String}
     */
    String regex() default "";

    /**
     * 消息
     *
     * @return {@link String}
     */
    String message() default "{cn.yhm.developer.kuca.validation.annotation.PatternValid.message}";

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
}
