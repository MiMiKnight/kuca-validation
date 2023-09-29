package com.github.mimiknight.kuca.validation.action;

import com.github.mimiknight.kuca.validation.exception.ValidationException;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Map;

/**
 * 注解描述类
 *
 * @author MiMiKnight victor2015yhm@gmail.com
 * @since 2023-09-24 23:24:50
 */
public class AnnotationDescriptor<A extends Annotation> implements Serializable {

    private static final long serialVersionUID = 5051658628493775642L;
    private final Class<A> type;
    private final transient A annotation;
    private final transient Map<String, Object> attributes;
    private final int hashCode;

    @SuppressWarnings("unchecked")
    public AnnotationDescriptor(A annotation) {
        this.type = (Class<A>) annotation.annotationType();
        this.annotation = annotation;
        this.attributes = run(GetAnnotationAttributes.action(annotation));
        this.hashCode = buildHashCode();
    }

    public AnnotationDescriptor(AnnotationDescriptor<A> descriptor) {
        this.type = descriptor.type;
        this.annotation = descriptor.annotation;
        this.attributes = descriptor.attributes;
        this.hashCode = descriptor.hashCode;
    }

    public Class<A> getType() {
        return type;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public A getAnnotation() {
        return annotation;
    }

    @Override
    public int hashCode() {
        return hashCode;
    }

    public boolean hasAttribute(String attributeName) {
        return attributes.containsKey(attributeName);
    }

    @SuppressWarnings("unchecked")
    public <T> T getAttribute(String attributeName, Class<T> attributeType) {
        Object attribute = attributes.get(attributeName);

        if (null == attribute) {
            return null;
        }

        if (!attributeType.isAssignableFrom(attribute.getClass())) {
            String format = "Wrong type for attribute '%2$s' of annotation %1$s. Expected: %3$s. Actual: %4$s.";
            String message = String.format(format, type, attributeName, attributeType, attribute.getClass());
            throw new ValidationException(message);
        }

        return (T) attribute;
    }

    public <T> T getMandatoryAttribute(String attributeName, Class<T> attributeType) {
        T attribute = getAttribute(attributeName, attributeType);

        if (null == attribute) {
            String format = "The specified annotation %1$s defines no attribute '%2$s'.";
            String message = String.format(format, type, attributeName);
            throw new ValidationException(message);
        }

        return attribute;
    }

    private int buildHashCode() {
        return hashCode();
    }

    private static <V> V run(PrivilegedAction<V> action) {
        return System.getSecurityManager() != null ? AccessController.doPrivileged(action) : action.run();
    }

    public static class Builder<S extends Annotation> {
        private S annotation;

        public Builder<S> setAnnotation(S annotation) {
            Assert.notNull(annotation, "Parameter annotation should not be null.");
            this.annotation = annotation;
            return this;
        }

        public AnnotationDescriptor<S> build() {
            return new AnnotationDescriptor<>(annotation);
        }

    }
}
