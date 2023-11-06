package com.github.mimiknight.kuca.validation.action;


import org.hibernate.validator.internal.util.annotation.AnnotationDescriptor;

import javax.validation.ConstraintTarget;
import javax.validation.Payload;
import java.lang.annotation.Annotation;
import java.util.Map;

/**
 * 约束注解描述类
 *
 * @author MiMiKnight victor2015yhm@gmail.com
 * @date 2023-11-06 23:31:45
 * @since 2023-09-24 23:50:20
 */
public class KucaConstraintAnnotationDescriptor<A extends Annotation> extends AnnotationDescriptor<A> {

    private static final long serialVersionUID = 1924242135638034072L;

    public KucaConstraintAnnotationDescriptor(A annotation) {
        super(annotation);
    }

    public KucaConstraintAnnotationDescriptor(AnnotationDescriptor<A> descriptor) {
        super(descriptor);
    }

    /**
     * 获取错误码
     */
    public String getErrorCode() {
        return getMandatoryAttribute(KucaConstraintHelper.ERROR_CODE, String.class);
    }

    /**
     * 获取提示消息
     */
    public String getMessage() {
        return getMandatoryAttribute(KucaConstraintHelper.MESSAGE, String.class);
    }

    /**
     * 获取分组
     */
    public Class<?>[] getGroups() {
        return getMandatoryAttribute(KucaConstraintHelper.GROUPS, Class[].class);
    }

    /**
     * 获取载荷
     */
    @SuppressWarnings("unchecked")
    public Class<? extends Payload>[] getPayload() {
        return getMandatoryAttribute(KucaConstraintHelper.PAYLOAD, Class[].class);
    }

    /**
     * 获取 应用目标
     */
    public ConstraintTarget getValidationAppliesTo() {
        return getAttribute(KucaConstraintHelper.VALIDATION_APPLIES_TO, ConstraintTarget.class);
    }

    public static class Builder<S extends Annotation> extends AnnotationDescriptor.Builder<S> {

        public Builder(Class<S> type) {
            super(type);
        }

        public Builder(Class<S> type, Map<String, Object> attributes) {
            super(type, attributes);
        }

        public Builder(S annotation) {
            super(annotation);
        }

        public KucaConstraintAnnotationDescriptor.Builder<S> setErrorCode(String errorCode) {
            setAttribute(KucaConstraintHelper.ERROR_CODE, errorCode);
            return this;
        }

        public KucaConstraintAnnotationDescriptor.Builder<S> setMessage(String message) {
            setAttribute(KucaConstraintHelper.MESSAGE, message);
            return this;
        }

        public KucaConstraintAnnotationDescriptor.Builder<S> setGroups(Class<?>[] groups) {
            setAttribute(KucaConstraintHelper.GROUPS, groups);
            return this;
        }

        public KucaConstraintAnnotationDescriptor.Builder<S> setPayload(Class<?>[] payload) {
            setAttribute(KucaConstraintHelper.PAYLOAD, payload);
            return this;
        }

        public KucaConstraintAnnotationDescriptor.Builder<S> setValidationAppliesTo(ConstraintTarget validationAppliesTo) {
            setAttribute(KucaConstraintHelper.VALIDATION_APPLIES_TO, validationAppliesTo);
            return this;
        }

        @Override
        public KucaConstraintAnnotationDescriptor<S> build() {
            return new KucaConstraintAnnotationDescriptor<>(super.build());
        }
    }
}