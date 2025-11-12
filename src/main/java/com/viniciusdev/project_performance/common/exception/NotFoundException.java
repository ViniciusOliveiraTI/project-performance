package com.viniciusdev.project_performance.common.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends RuntimeException {

    private final HttpStatus status;
    private final String code;

    public NotFoundException(String message, String code, HttpStatus status) {
        super(message);
        this.code = code;
        this.status = status;
    }

    public NotFoundException(String message) {
        this(message, "NOT_FOUND", HttpStatus.NOT_FOUND);
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getCode() {
        return code;
    }
}
