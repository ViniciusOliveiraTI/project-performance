package com.viniciusdev.project_performance.app.exception;

import com.viniciusdev.project_performance.app.exception.dtos.FieldValidationError;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;
import java.time.Instant;
import java.util.List;
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

    @ExceptionHandler(ForbiddenException.class)
    public ProblemDetail handleForbidden(ForbiddenException e, HttpServletRequest request) {
        ProblemDetail pd = ProblemDetail.forStatusAndDetail(e.getStatus(), e.getMessage());
        pd.setTitle("Forbidden");
        pd.setType(URI.create("http://localhost:8080/problems/forbidden"));
        pd.setInstance(URI.create(request.getRequestURI()));
        pd.setProperties(Map.of("error", e.getCode(), "timestamp", Instant.now()));
        return pd;
}

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ProblemDetail handleEmailAlreadyExists(EmailAlreadyExistsException e, HttpServletRequest request) {
        ProblemDetail pd = ProblemDetail.forStatusAndDetail(e.getStatus(), e.getMessage());
        pd.setTitle("Email Already Exists");
        pd.setType(URI.create("http://localhost:8080/problems/email-already-exists"));
        pd.setInstance(URI.create(request.getRequestURI()));
        pd.setProperties(Map.of("error", e.getCode(), "timestamp", Instant.now()));
        return pd;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleMethodArgumentNotValid(MethodArgumentNotValidException e, HttpServletRequest request) {

        List<FieldValidationError> fieldErrors = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fe -> new FieldValidationError(
                        fe.getField(),
                        fe.getDefaultMessage()
                ))
                .toList();

        ProblemDetail pd = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "One or more fields are invalid");
        pd.setTitle("Invalid fields in request");
        pd.setType(URI.create("http://localhost:8080/problems/invalid-fields"));
        pd.setInstance(URI.create(request.getRequestURI()));
        pd.setProperties(Map.of("error", HttpStatus.BAD_REQUEST.name(), "timestamp", Instant.now(), "fieldErrors", fieldErrors));

        return pd;
    }
}
