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
 * @since 2023-09-24 23:50:20
 */
public class ConstraintAnnotationDescriptor<A extends Annotation> extends AnnotationDescriptor<A> {

    private static final long serialVersionUID = 1924242135638034072L;

    public ConstraintAnnotationDescriptor(A annotation) {
        super(annotation);
    }

    public ConstraintAnnotationDescriptor(AnnotationDescriptor<A> descriptor) {
        super(descriptor);
    }

    /**
     * 获取错误码
     */
    public String getErrorCode() {
        return getMandatoryAttribute(ConstraintHelper.ERROR_CODE, String.class);
    }

    /**
     * 获取提示消息
     */
    public String getMessage() {
        return getMandatoryAttribute(ConstraintHelper.MESSAGE, String.class);
    }

    /**
     * 获取分组
     */
    public Class<?>[] getGroups() {
        return getMandatoryAttribute(ConstraintHelper.GROUPS, Class[].class);
    }

    /**
     * 获取载荷
     */
    @SuppressWarnings("unchecked")
    public Class<? extends Payload>[] getPayload() {
        return getMandatoryAttribute(ConstraintHelper.PAYLOAD, Class[].class);
    }

    /**
     * 获取 应用目标
     */
    public ConstraintTarget getValidationAppliesTo() {
        return getAttribute(ConstraintHelper.VALIDATION_APPLIES_TO, ConstraintTarget.class);
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

        public ConstraintAnnotationDescriptor.Builder<S> setErrorCode(String errorCode) {
            setAttribute(ConstraintHelper.ERROR_CODE, errorCode);
            return this;
        }

        public ConstraintAnnotationDescriptor.Builder<S> setMessage(String message) {
            setAttribute(ConstraintHelper.MESSAGE, message);
            return this;
        }

        public ConstraintAnnotationDescriptor.Builder<S> setGroups(Class<?>[] groups) {
            setAttribute(ConstraintHelper.GROUPS, groups);
            return this;
        }

        public ConstraintAnnotationDescriptor.Builder<S> setPayload(Class<?>[] payload) {
            setAttribute(ConstraintHelper.PAYLOAD, payload);
            return this;
        }

        public ConstraintAnnotationDescriptor.Builder<S> setValidationAppliesTo(ConstraintTarget validationAppliesTo) {
            setAttribute(ConstraintHelper.VALIDATION_APPLIES_TO, validationAppliesTo);
            return this;
        }

        @Override
        public ConstraintAnnotationDescriptor<S> build() {
            return new ConstraintAnnotationDescriptor<>(super.build());
        }
    }
}