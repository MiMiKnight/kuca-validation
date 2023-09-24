package com.github.mimiknight.kuca.validation.exception;

public class ValidationException extends RuntimeException {

    private static final long serialVersionUID = -3664684893897729208L;

    public ValidationException(String message) {
        super(message);
    }

    public ValidationException() {
        super();
    }

    public ValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidationException(Throwable cause) {
        super(cause);
    }
}
