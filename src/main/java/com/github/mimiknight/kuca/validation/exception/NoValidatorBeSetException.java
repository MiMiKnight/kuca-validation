package com.github.mimiknight.kuca.validation.exception;

/**
 * 没有Validator被设置异常
 *
 * @author MiMiKnight victor2015yhm@gmail.com
 * @since 2023-09-17 09:56:16
 */
public class NoValidatorBeSetException extends RuntimeException {
    private static final long serialVersionUID = 346088924906832174L;

    public NoValidatorBeSetException(String message) {
        super(message);
    }

    public NoValidatorBeSetException(String message, Throwable cause) {
        super(message, cause);
    }
}
