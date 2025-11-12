package com.viniciusdev.project_performance.common.exception;

import org.springframework.http.HttpStatus;

public class DataIntegrityException extends RuntimeException {

    private final HttpStatus status;
    private final String code;

    public DataIntegrityException(String message, String code, HttpStatus status) {
        super(message);
        this.code = code;
        this.status = status;
    }

    public DataIntegrityException(String message) {
        this(message, "DATA_INTEGRITY_VIOLATION", HttpStatus.CONFLICT);
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getCode() {
        return code;
    }
}
