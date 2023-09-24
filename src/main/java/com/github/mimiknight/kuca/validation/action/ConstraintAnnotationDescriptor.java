package com.github.mimiknight.kuca.validation.action;

import java.lang.annotation.Annotation;

/**
 * 约束注解描述类
 *
 * @author MiMiKnight victor2015yhm@gmail.com
 * @since 2023-09-24 23:50:20
 */
public class ConstraintAnnotationDescriptor<A extends Annotation> extends AnnotationDescriptor<A> {

    private static final long serialVersionUID = -690092475442072888L;

    public ConstraintAnnotationDescriptor(A annotation) {
        super(annotation);
    }

    public ConstraintAnnotationDescriptor(AnnotationDescriptor<A> descriptor) {
        super(descriptor);
    }

    public String getErrorCode() {
        return getMandatoryAttribute("errorCode", String.class);
    }
    
    public String getMessage() {
        return getMandatoryAttribute("message", String.class);
    }

}
