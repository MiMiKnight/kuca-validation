package cn.yhm.developer.kuca.validation.annotation.validation;

import cn.yhm.developer.kuca.validation.annotation.Constraint;
import cn.yhm.developer.kuca.validation.validator.NotEmptyValidator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 参数非空校验注解
 *
 * <p>
 * 字符串的字符个数不为零 {@link String}
 * <p>
 * 数组的元素个数不为零
 * <p>
 * 单列集合的元素个数不为零 {@link java.util.Collection}
 * <p>
 * 双列集合的元素个数不为零 {@link java.util.Map}
 *
 * @author victor2015yhm@gmail.com
 * @since 2023-06-07 20:05:34
 */
@Constraint(validatedBy = {NotEmptyValidator.class})
@Documented
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = {ElementType.FIELD})
public @interface ValidateNotEmpty {

    /**
     * 消息
     *
     * @return {@link String}
     */
    String message() default "{cn.yhm.developer.kuca.validation.annotation.NotEmptyValid.message}";

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
