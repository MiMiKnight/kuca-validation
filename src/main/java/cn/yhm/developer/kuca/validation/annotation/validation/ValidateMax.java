package cn.yhm.developer.kuca.validation.annotation.validation;

import cn.yhm.developer.kuca.validation.annotation.Constraint;
import cn.yhm.developer.kuca.validation.validator.SizeValidator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 最大值校验注解
 *
 * @author victor2015yhm@gmail.com
 * @since 2023-06-07 20:05:34
 */
@Constraint(validatedBy = {SizeValidator.class})
@Documented
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = {ElementType.FIELD})
public @interface ValidateMax {

    /**
     * 最大值
     *
     * @return int
     */
    double max() default Integer.MAX_VALUE;

    /**
     * 精确度
     * <p>
     * 默认：10负6次方
     *
     * @return double
     */
    double delta() default 1E-6;

    /**
     * 消息
     *
     * @return {@link String}
     */
    String message() default "{cn.yhm.developer.kuca.validation.annotation.MaxValid.message}";

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
