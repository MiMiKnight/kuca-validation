package com.github.mimiknight.kuca.validation.action;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.mimiknight.kuca.validation.exception.ValidationException;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.internal.engine.ConstraintViolationImpl;
import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.metadata.ConstraintDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 约束帮助类
 *
 * @author MiMiKnight victor2015yhm@gmail.com
 * @since 2023-09-28 00:43:27
 */
public class KucaConstraintHelper {

    private KucaConstraintHelper() {
    }

    public static final String MESSAGE = "message";
    public static final String ERROR_CODE = "errorCode";
    public static final String GROUPS = "groups";
    public static final String PAYLOAD = "payload";
    public static final String VALIDATION_APPLIES_TO = "validationAppliesTo";

    /**
     * 获取目标对象的全部成员属性字段
     * <p>
     * 包括全部父类的继承属性
     *
     * @param target 目标对象
     * @return {@link List}<{@link Field}>
     */
    @Validated
    public static List<Field> getFields(@NotNull Object target) {
        Class<?> targetClass = target.getClass();

        LinkedList<Field> fields = new LinkedList<>();

        while (null != targetClass) {
            Field[] declaredFields = targetClass.getDeclaredFields();
            List<Field> fieldList = Arrays.stream(declaredFields).collect(Collectors.toList());
            fields.addAll(fieldList);
            targetClass = targetClass.getSuperclass();
        }
        return fields;
    }

    /**
     * 根据字段属性名称和字段数据类型反射获取字段Field对象
     *
     * @param target    目标对象
     * @param fieldName 字段属性名称
     * @param fieldType 字段类型
     * @return {@link Field}
     */
    @Validated
    public static <T> Field getFieldByNameAndType(@NotNull Object target,
                                                  @NotBlank String fieldName,
                                                  @NotNull Class<T> fieldType) {
        List<Field> fields = getFields(target);

        if (CollectionUtils.isEmpty(fields)) {
            return null;
        }

        for (Field field : fields) {
            if (!fieldName.equals(field.getName())) {
                continue;
            }
            ReflectionUtils.makeAccessible(field);
            Object fieldValue = ReflectionUtils.getField(field, target);
            if (fieldType.isInstance(fieldValue)) {
                return field;
            }
        }
        return null;
    }

    /**
     * 获取反射字段值
     *
     * @param target 目标对象
     * @param field  反射字段
     * @return {@link Object}
     */
    public static <T> Object getFieldValue(T target, Field field) {
        ReflectionUtils.makeAccessible(field);
        return ReflectionUtils.getField(field, target);
    }


    /**
     * 获取ConstraintViolation
     *
     * @param fieldError 字段错误对象
     * @return {@link ConstraintViolationImpl}
     */
    @SuppressWarnings({"rawtypes"})
    public static ConstraintViolationImpl getConstraintViolation(FieldError fieldError) {
        String sourceFieldName = "source";
        Field sourceField = getFieldByNameAndType(fieldError, sourceFieldName, ConstraintViolationImpl.class);
        if (null == sourceField) {
            return null;
        }
        Object sourceFieldValue = getFieldValue(fieldError, sourceField);
        if (sourceFieldValue instanceof ConstraintViolationImpl) {
            return (ConstraintViolationImpl) sourceFieldValue;
        }
        return null;
    }

    /**
     * 获取约束注解
     *
     * @param violation 约束冲突实例
     * @return {@link Annotation}
     */
    @SuppressWarnings({"rawtypes"})
    public static Annotation getConstraintAnnotation(ConstraintViolationImpl violation) {
        ConstraintDescriptor descriptor = violation.getConstraintDescriptor();
        return descriptor.getAnnotation();
    }

    /**
     * 获取 KucaConstraintAnnotationDescriptor
     *
     * @param annotation 注解实例
     * @return {@link KucaConstraintAnnotationDescriptor}<{@link A}>
     */
    public static <A extends Annotation> KucaConstraintAnnotationDescriptor<A> getKucaConstraintAnnotationDescriptor(A annotation) {
        return new KucaConstraintAnnotationDescriptor.Builder<>(annotation).build();
    }


    /**
     * 获取字段展示名称
     *
     * @param field 反射字段
     * @return {@link String}
     */
    public static String getFieldDisplayName(Field field) {
        String fieldName = field.getName();
        String jsonName = null;

        if (field.isAnnotationPresent(JsonProperty.class)) {
            JsonProperty jsonProperty = field.getAnnotation(JsonProperty.class);
            jsonName = jsonProperty.value();
        }

        return StringUtils.isNotBlank(jsonName) ? jsonName : fieldName;
    }

    @Validated
    public static String getFieldDisplayName(@NotNull Object target, @NotBlank String fieldName) {
        try {
            Field field = target.getClass().getField(fieldName);
            return getFieldDisplayName(field);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 判断是否为自定义的校验注解
     *
     * @param annotation 注解
     * @return boolean
     */
    public static <A extends Annotation> boolean isConstraintAnnotation(A annotation) {
        Assert.notNull(annotation, "Parameter must not be null.");

        Class<?> targetClass = annotation.getClass();

        // 目标对象不是注解类型，则非校验注解
        if (!targetClass.isAnnotation()) {
            return false;
        }

        // 目标对象没有被指定注解修饰，则非校验注解
        if (!targetClass.isAnnotationPresent(Constraint.class)) {
            return false;
        }

        // 如果为校验注解，则Validator不允许为空
        Constraint constraint = targetClass.getDeclaredAnnotation(Constraint.class);
        Class<? extends ConstraintValidator<?, ?>>[] validatorClazzArrays = constraint.validatedBy();
        if (ArrayUtils.isEmpty(validatorClazzArrays)) {
            return false;
        }

        KucaConstraintAnnotationDescriptor.Builder<A> builder = new KucaConstraintAnnotationDescriptor.Builder<>(annotation);
        KucaConstraintAnnotationDescriptor<A> descriptor = builder.build();

        // 目标对象属性为空，则非校验注解
        Map<String, Object> attributes = descriptor.getAttributes();
        if (MapUtils.isEmpty(attributes)) {
            return false;
        }

        Object errorCode = attributes.get(ERROR_CODE);
        Object message = attributes.get(MESSAGE);
        Object group = attributes.get(GROUPS);
        Object payload = attributes.get(PAYLOAD);

        // 目标对象指定属性不存在，则非校验注解
        return (null != errorCode && null != message && null != group && null != payload);
    }


    public static <A extends Annotation, V> List<ConstraintValidator<A, V>> getValidators(A annotation) {
        Class<? extends Annotation> clazz = annotation.getClass();
        Constraint constraint = clazz.getDeclaredAnnotation(Constraint.class);
        Class<? extends ConstraintValidator<?, ?>>[] validatedBy = constraint.validatedBy();

        return Arrays.stream(validatedBy).map(v -> {
            try {
                return (ConstraintValidator<A, V>) v.getDeclaredConstructor().newInstance();
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                     NoSuchMethodException ex) {
                String format = "%s create instance failed.";
                String tip = String.format(format, v.getName());
                throw new ValidationException(tip, ex);
            }
        }).collect(Collectors.toList());

    }

}
