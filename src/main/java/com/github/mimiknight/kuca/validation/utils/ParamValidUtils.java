package com.github.mimiknight.kuca.validation.utils;

/**
 * 参数校验工具类
 *
 * @author MiMiKnight victor2015yhm@gmail.com
 * @since 2023-09-15 01:12:15
 */
public class ParamValidUtils {

    public static void validNull(Object value, RuntimeException ex) {
        if (null == value) {
            return;
        }
        throw ex;
    }

    public static void validNotNull(Object value, RuntimeException ex) {
        if (null != value) {
            return;
        }
        throw ex;
    }

    public static void validEmpty(Object value, RuntimeException ex) {

    }

    public static void validNotEmpty(Object value, RuntimeException ex) {

    }

    public static void validBlank(String value, RuntimeException ex) {

    }

    public static void validNotBlank(String value, RuntimeException ex) {

    }
}
