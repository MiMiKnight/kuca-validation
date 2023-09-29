package com.github.mimiknight.kuca.validation.action;

import com.github.mimiknight.kuca.validation.constant.Scope;
import com.github.mimiknight.kuca.validation.exception.ValidationException;

import java.lang.annotation.Annotation;

/**
 * 约束注解描述类
 *
 * @author MiMiKnight victor2015yhm@gmail.com
 * @since 2023-09-24 23:50:20
 */
public final class ConstraintAnnotationDescriptor<A extends Annotation> extends AnnotationDescriptor<A> {

    private static final long serialVersionUID = -690092475442072888L;

    private ConstraintAnnotationDescriptor(A annotation) {
        super(annotation);
    }

    private ConstraintAnnotationDescriptor(AnnotationDescriptor<A> descriptor) {
        super(descriptor);
    }

    public String getErrorCode() {
        return getMandatoryAttribute(ConstraintHelper.ERROR_CODE, String.class);
    }

    public String getMessage() {
        return getMandatoryAttribute(ConstraintHelper.MESSAGE, String.class);
    }

    public Scope getScope() {
        return getMandatoryAttribute(ConstraintHelper.SCOPE, Scope.class);
    }

    public Class<?>[] getGroups() {
        return getMandatoryAttribute(ConstraintHelper.GROUPS, Class[].class);
    }

    public static class Builder<S extends Annotation> extends AnnotationDescriptor.Builder<S> {

        @Override
        public ConstraintAnnotationDescriptor.Builder<S> setAnnotation(S annotation) {

            if (!ConstraintHelper.isConstraintAnnotation(annotation)) {
                throw new ValidationException("The parameter annotation is not constraint annotation type.");
            }

            super.setAnnotation(annotation);
            return this;
        }

        @Override
        public ConstraintAnnotationDescriptor<S> build() {
            return new ConstraintAnnotationDescriptor<>(super.build());
        }
    }

}
