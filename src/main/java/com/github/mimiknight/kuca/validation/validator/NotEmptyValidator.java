package com.github.mimiknight.kuca.validation.validator;

import com.github.mimiknight.kuca.validation.annotation.validation.NotEmpty;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;

/**
 * 参数非空校验器
 *
 * @author victor2015yhm@gmail.com
 * @since 2023-06-07 20:13:23
 */
public class NotEmptyValidator implements ConstraintValidator<NotEmpty, Object> {
    @Override
    public void initialize(NotEmpty constraintAnnotation) {
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
            return size >= 1;
        }
        // 数组
        if (value.getClass().isArray()) {
            size = Array.getLength(value);
            return size >= 1;
        }
        // 单列集合
        if (value instanceof Collection) {
            size = ((Collection<?>) value).size();
            return size >= 1;
        }
        // 双列集合
        if (value instanceof Map) {
            size = ((Map<?, ?>) value).size();
            return size >= 1;
        }
        // 默认放通
        return true;
    }
}
