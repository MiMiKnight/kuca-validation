package cn.yhm.developer.kuca.validation.validator;

import cn.yhm.developer.kuca.validation.annotation.validation.ValidateSize;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;

/**
 * 元素个数注解校验器
 *
 * @author victor2015yhm@gmail.com
 * @since 2023-06-07 20:13:23
 */
public class SizeValidator implements ConstraintValidator<ValidateSize, Object> {

    private int min;
    private int max;

    @Override
    public void initialize(ValidateSize constraintAnnotation) {
        this.min = constraintAnnotation.min();
        this.max = constraintAnnotation.max();
    }

    @Override
    public boolean isValid(Object value) {
        if (null == value) {
            return true;
        }
        int size;
        // 字符串
        if (value instanceof String) {
            size = ((String) value).length();
            return (size >= min && size <= max);
        }
        // 数组
        if (value.getClass().isArray()) {
            size = Array.getLength(value);
            return (size >= min && size <= max);
        }
        // 单列集合
        if (value instanceof Collection) {
            size = ((Collection<?>) value).size();
            return (size >= min && size <= max);
        }
        // 双列集合
        if (value instanceof Map) {
            size = ((Map<?, ?>) value).size();
            return (size >= min && size <= max);
        }
        // 默认放通
        return true;
    }
}
