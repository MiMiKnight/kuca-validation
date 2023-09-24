package com.github.mimiknight.kuca.validation.action;

import com.github.mimiknight.kuca.validation.exception.ValidationException;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.PrivilegedAction;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 获取注解属性
 *
 * @author MiMiKnight victor2015yhm@gmail.com
 * @since 2023-09-24 22:53:23
 */
public final class GetAnnotationAttributes implements PrivilegedAction<Map<String, Object>> {

    private final Annotation annotation;

    public static GetAnnotationAttributes action(Annotation annotation) {
        return new GetAnnotationAttributes(annotation);
    }

    private GetAnnotationAttributes(Annotation annotation) {
        this.annotation = annotation;
    }

    @Override
    public Map<String, Object> run() {
        final Method[] declaredMethods = annotation.annotationType().getDeclaredMethods();
        Map<String, Object> attributes = new HashMap<>(declaredMethods.length);

        for (Method m : declaredMethods) {
            // Exclude synthetic methods
            if (m.isSynthetic()) {
                continue;
            }

            m.setAccessible(true);

            String attributeName = m.getName();

            try {
                attributes.put(m.getName(), m.invoke(annotation));
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                String format = "Unable to get attribute '%2$s' from annotation %1$s.";
                String message = String.format(format, annotation.getClass(), attributeName);
                throw new ValidationException(message, e);
            }
        }
        return toImmutableMap(attributes);
    }

    private <K, V> Map<K, V> toImmutableMap(Map<K, V> map) {
        switch (map.size()) {
            case 0:
                return Collections.emptyMap();
            case 1:
                Map.Entry<K, V> entry = map.entrySet().iterator().next();
                return Collections.singletonMap(entry.getKey(), entry.getValue());
            default:
                return Collections.unmodifiableMap(map);
        }
    }

}
