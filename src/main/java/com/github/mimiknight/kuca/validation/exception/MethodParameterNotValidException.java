package com.github.mimiknight.kuca.validation.exception;

/**
 * 方法参数校验未通过异常
 *
 * @author victor2015yhm@gmail.com
 * @since 2023-06-07 20:16:55
 */
public class MethodParameterNotValidException extends RuntimeException {

    /**
     * 注解校验未通过异常 构造方法
     *
     * @param message 消息
     */
    public MethodParameterNotValidException(String message) {
        super(message);
    }
}
