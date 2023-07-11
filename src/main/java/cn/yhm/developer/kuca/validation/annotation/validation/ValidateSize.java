package cn.yhm.developer.kuca.validation.annotation.validation;

import cn.yhm.developer.kuca.validation.annotation.Constraint;
import cn.yhm.developer.kuca.validation.validator.SizeValidator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

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
@Constraint(validatedBy = {SizeValidator.class})
@Documented
@Target(value = {ElementType.FIELD, ElementType.LOCAL_VARIABLE})
@Retention(value = RetentionPolicy.RUNTIME)
@Repeatable(value = ValidateSize.List.class)
public @interface ValidateSize {

    /**
     * 最小值
     *
     * @return int
     */
    int min() default 0;

    /**
     * 最大值
     *
     * @return int
     */
    int max() default Integer.MAX_VALUE;

    /**
     * 消息
     *
     * @return {@link String}
     */
    String message() default "{cn.yhm.developer.kuca.validation.annotation.ValidateSize.message}";

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
        ValidateSize[] value();
    }
}
