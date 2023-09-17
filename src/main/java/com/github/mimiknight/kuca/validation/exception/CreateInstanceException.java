package com.github.mimiknight.kuca.validation.exception;

/**
 * 创建实例异常
 *
 * @author MiMiKnight victor2015yhm@gmail.com
 * @since 2023-09-17 09:56:16
 */
public class CreateInstanceException extends RuntimeException {
    private static final long serialVersionUID = -7516111505186197688L;

    public CreateInstanceException(String message) {
        super(message);
    }

    public CreateInstanceException(String message, Throwable cause) {
        super(message, cause);
    }
}
