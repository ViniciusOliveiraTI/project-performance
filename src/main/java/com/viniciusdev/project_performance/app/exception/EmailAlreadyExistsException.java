package com.viniciusdev.project_performance.app.exception;

import org.springframework.http.HttpStatus;

public class EmailAlreadyExistsException extends RuntimeException {

    private final HttpStatus status;
    private final String code;

    public EmailAlreadyExistsException(String message, String code, HttpStatus status) {
        super(message);
        this.code = code;
        this.status = status;
    }

    public EmailAlreadyExistsException(String email) {
        this("Email '%s' already exists".formatted(email), "CONFLICT", HttpStatus.CONFLICT);
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getCode() {
        return code;
    }
}
