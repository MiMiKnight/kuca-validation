package com.github.mimiknight.kuca.validation.action;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.mimiknight.kuca.validation.exception.ValidationException;
import com.github.mimiknight.kuca.validation.model.AnnotationParamValidErrorTip;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.internal.engine.ConstraintViolationImpl;
import org.hibernate.validator.internal.engine.path.NodeImpl;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;

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
@Slf4j
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
    public static <T> Field getFieldByNameAndType(@NotNull Object target, @NotBlank String fieldName, @NotNull Class<T> fieldType) {
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
    public static <A extends Annotation> KucaConstraintAnnotationDescriptor<A> createKucaConstraintAnnotationDescriptor(A annotation) {
        return new KucaConstraintAnnotationDescriptor.Builder<>(annotation).build();
    }

    /**
     * 获取字段名
     *
     * @param violation 约束冲突
     * @return {@link String}
     */
    @SuppressWarnings({"rawtypes"})
    public static String getFieldName(ConstraintViolationImpl violation) {

        PathImpl propertyPath = (PathImpl) violation.getPropertyPath();
        NodeImpl leafNode = propertyPath.getLeafNode();

        // 当注解校验的元素 不是数组、Collection或者Map的子元素时，此name为属性名
        String name = leafNode.getName();
        // 当注解校验的元素是数组、Collection或者Map的子元素时，parentName才为字段属性名
        String parentName = leafNode.getParent().getName();

        return (null == parentName) ? name : parentName;
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
            Field field = target.getClass().getDeclaredField(fieldName);
            return getFieldDisplayName(field);
        } catch (NoSuchFieldException e) {
            throw new ValidationException(e);
        }
    }

    /**
     * 获取注解校验错误信息提示对象
     *
     * @param ex MethodArgumentNotValidException
     * @return {@link AnnotationParamValidErrorTip}
     */
    @SuppressWarnings({"rawtypes"})
    public static AnnotationParamValidErrorTip createAnnotationParamValidErrorTip(MethodArgumentNotValidException ex) {

        AnnotationParamValidErrorTip tip = new AnnotationParamValidErrorTip();

        BindingResult bindingResult = ex.getBindingResult();
        // 目标对象
        Object target = bindingResult.getTarget();

        if (null == target) {
            return tip;
        }

        // 字段错误对象
        FieldError fieldError = bindingResult.getFieldError();

        if (null == fieldError) {
            return tip;
        }

        // 错误提示消息
        String errorTipMessage = fieldError.getDefaultMessage();
        // 约束冲突实现类
        ConstraintViolationImpl violation = KucaConstraintHelper.getConstraintViolation(fieldError);

        if (null == violation) {
            return tip;
        }

        // 原生约束注解对象
        Annotation constraintAnnotation = KucaConstraintHelper.getConstraintAnnotation(violation);
        // Kuca约束注解描述对象
        KucaConstraintAnnotationDescriptor<Annotation> descriptor = createKucaConstraintAnnotationDescriptor(constraintAnnotation);
        // 错误码
        String errorCode = descriptor.getErrorCode();
        // 字段展示名称
        String fieldName = getFieldName(violation);
        // 字段展示名称
        String fieldDisplayName = getFieldDisplayName(target, fieldName);

        return tip.setErrorCode(errorCode).setFieldName(fieldDisplayName).setMessage(errorTipMessage);
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


    @SuppressWarnings({"unchecked"})
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
