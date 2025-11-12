package com.viniciusdev.project_performance.app;

import com.viniciusdev.project_performance.common.exception.DataIntegrityException;
import com.viniciusdev.project_performance.common.exception.NotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;
import java.time.Instant;
import java.util.Map;

@RestControllerAdvice
public class AppControllerAdvice {

    @ExceptionHandler(NotFoundException.class)
    public ProblemDetail handleNotFound(NotFoundException e, HttpServletRequest request) {
        ProblemDetail pd = ProblemDetail.forStatusAndDetail(e.getStatus(), e.getMessage());
        pd.setTitle("Resource not found");
        pd.setType(URI.create("http://localhost:8080/problems/not-found"));
        pd.setInstance(URI.create(request.getRequestURI()));
        pd.setProperties(Map.of("error", e.getCode(), "timestamp", Instant.now()));
        return pd;
    }

    @ExceptionHandler(DataIntegrityException.class)
    public ProblemDetail handleDataIntegrityViolation(DataIntegrityException e, HttpServletRequest request) {
        ProblemDetail pd = ProblemDetail.forStatusAndDetail(e.getStatus(), e.getMessage());
        pd.setTitle("Data integrity constraint violated");
        pd.setType(URI.create("http://localhost:8080/problems/data-integrity-violation"));
        pd.setInstance(URI.create(request.getRequestURI()));
        pd.setProperties(Map.of("error", e.getCode(), "timestamp", Instant.now()));
        return pd;
    }

}
