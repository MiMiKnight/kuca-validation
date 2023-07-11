package cn.yhm.developer.kuca.validation.utils;

import cn.yhm.developer.kuca.validation.annotation.Constraint;
import cn.yhm.developer.kuca.validation.validator.ConstraintValidator;

import java.lang.annotation.Annotation;

/**
 * 验证跑龙套
 *
 * @author victor2015yhm@gmail.com
 * @since 2023-06-13 21:08:38
 */
public class ValidateUtils {

    public <T, A extends Annotation> boolean validate(A validateAnnotation, T value) {

        Class<? extends Annotation> validateAnnotaionClass = validateAnnotation.getClass();
        Constraint constraint = validateAnnotaionClass.getAnnotation(Constraint.class);
        if (null == constraint) {
            return true;
        }
        Class<? extends ConstraintValidator<?, ?>>[] validatorClazzArrays = constraint.validatedBy();
        for (Class<? extends ConstraintValidator<?, ?>> validatorClazz : validatorClazzArrays) {
//            ConstraintValidator<?, ?> validator = validatorClazz.getDeclaredConstructor().newInstance();
//            validator.isValid(value);
        }

        return false;
    }

}
