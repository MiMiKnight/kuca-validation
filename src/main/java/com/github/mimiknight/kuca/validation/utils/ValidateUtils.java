package com.github.mimiknight.kuca.validation.utils;

import com.github.mimiknight.kuca.validation.annotation.Constraint;
import com.github.mimiknight.kuca.validation.annotation.Validated;
import com.github.mimiknight.kuca.validation.exception.CreateInstanceException;
import com.github.mimiknight.kuca.validation.exception.MethodParameterNotValidException;
import com.github.mimiknight.kuca.validation.exception.NoValidatorBeSetException;
import com.github.mimiknight.kuca.validation.validator.ConstraintValidator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * 校验工具类
 *
 * @author victor2015yhm@gmail.com
 * @since 2023-06-13 21:08:38
 */
@Slf4j
public class ValidateUtils {

    private ValidateUtils() {
    }

    public static <T> void validate(T target) {
        // 目标对象为空则不执行校验逻辑
        if (null == target) {
            return;
        }
        Class<?> targetClass = target.getClass();
        Validated validatedAnnotation = targetClass.getDeclaredAnnotation(Validated.class);
        // 目标对象没有被Validated注解注释则不执行校验逻辑
        if (null == validatedAnnotation) {
            return;
        }
        Field[] fields = targetClass.getDeclaredFields();
        // 没有设置成员变量则不执行校验逻辑
        if (ArrayUtils.isEmpty(fields)) {
            return;
        }
        for (Field field : fields) {
            // 获取字段值
            Object value = getFieldValue(target, field);
            // 获取字段java属性名
            String fieldName = field.getName();
            // TODO 获取字段 JSON名称
            // 获取字段上的注解数组
            Annotation[] annotations = field.getDeclaredAnnotations();
            for (Annotation annotation : annotations) {
                boolean result = validate(annotation, value);
                if (!result) {
                    throw new MethodParameterNotValidException("param valid not pass.");
                }
            }
        }
    }

    private static <T> Object getFieldValue(T target, Field field) {
        Object value;
        try {
            if (!field.isAccessible()) {
                field.setAccessible(true);
            }
            value = field.get(target);
        } catch (Exception e) {
            // TODO 待完善
            return null;
        }
        return value;
    }

    public static <V, A extends Annotation> boolean validate(A[] annotations, V value) {
        if (ArrayUtils.isEmpty(annotations)) {
            return true;
        }
        boolean result = false;
        for (A annotation : annotations) {
            // 如果校验未通过，则报错提醒
            if (!validate(annotation, value)) {
                return false;
            }
        }
        return result;
    }

    /**
     * 校验方法
     *
     * @param validateAnnotation 校验注解
     * @param value              需要被校验的值
     * @return boolean
     */
    public static <V, A extends Annotation> boolean validate(A validateAnnotation, V value) {
        Class<? extends Annotation> validateAnnotaionClass = validateAnnotation.annotationType();
        Constraint constraint = validateAnnotaionClass.getAnnotation(Constraint.class);
        if (null == constraint) {
            return true;
        }
        Class<? extends ConstraintValidator<?, ?>>[] validatorClazzArrays = constraint.validatedBy();
        // 该校验注解上未设置任何validator则报错提醒
        if (ArrayUtils.isEmpty(validatorClazzArrays)) {
            String name = validateAnnotaionClass.getName();
            String format = "%s does not set validator";
            log.error(String.format(format, name));
            throw new NoValidatorBeSetException("There is no validator be set.");
        }
        // 批量执行validator
        for (Class<? extends ConstraintValidator<?, ?>> validatorClazz : validatorClazzArrays) {
            ConstraintValidator<A, V> validator = getValidator(validatorClazz);
            // 初始化
            validator.initialize(validateAnnotation);
            // 执行校验
            if (!validator.isValid(value)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 获取校验器实例
     *
     * @param validatorClazz 校验器Class对象
     * @return {@link ConstraintValidator}<{@link A}, {@link V}>
     */
    @SuppressWarnings({"unchecked"})
    private static <V, A extends Annotation> ConstraintValidator<A, V> getValidator(Class<? extends ConstraintValidator<?, ?>> validatorClazz) {
        ConstraintValidator<A, V> validator;
        try {
            validator = (ConstraintValidator<A, V>) validatorClazz.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            String format = "%s be created instance failed.";
            String tip = String.format(format, validatorClazz.getName());
            throw new CreateInstanceException(tip, e);
        }
        return validator;
    }

}
