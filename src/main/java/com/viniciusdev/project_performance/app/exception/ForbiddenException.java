package com.viniciusdev.project_performance.app.exception;

import org.springframework.http.HttpStatus;

public class ForbiddenException extends RuntimeException {
    private final HttpStatus status;
    private final String code;

    public ForbiddenException(String message, String code, HttpStatus status) {
        super(message);
        this.code = code;
        this.status = status;
    }

    public ForbiddenException(String message) {
        this(message, "FORBIDDEN", HttpStatus.FORBIDDEN);
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getCode() {
        return code;
    }
}
