package com.webiti.crud.helper;

import lombok.Getter;

import java.io.Serial;

@Getter
public class ValidationException extends RuntimeException {

    private static final long serialVersionUID = 1203807538797953851L;

    private String code;
    private Object[] args;
    private Object payload;
    private Throwable cause;

    public ValidationException(ValidationErrors error, Object... args) {
        super(error.getDefaultMessage());
        this.code = error.name();
        this.args = args;
    }

    public ValidationException(Object payload, ValidationErrors error, Object... args) {
        super(error.getDefaultMessage());
        this.code = error.name();
        this.payload = payload;
        this.args = args;
    }

    public ValidationException(Throwable cause, ValidationErrors error, Object... args) {
        super(cause.getMessage() != null ? cause.getMessage() : error.getDefaultMessage(), cause);
        this.code = error.name();
        this.cause = cause;
        this.args = args;
    }

    public ValidationException(Throwable cause, Object payload, ValidationErrors error,
                               Object... args) {
        super(cause.getMessage() != null ? cause.getMessage() : error.getDefaultMessage(), cause);
        this.code = error.name();
        this.cause = cause;
        this.payload = payload;
        this.args = args;
    }
}

