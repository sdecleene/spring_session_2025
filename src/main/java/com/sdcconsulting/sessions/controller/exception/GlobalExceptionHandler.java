package com.sdcconsulting.sessions.controller.exception;

import com.sdcconsulting.sessions.service.exception.StudentNotFoundException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@Profile("!DEV")
@RestControllerAdvice
public class GlobalExceptionHandler {

    /*
        This generic exception handler catches all exceptions that are not filtered in other exception handlers.
         This is because the Exception class itself is the base class of any other exception.
         This one can be triggered by for example passing null as dateOfBirth
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGenericException(final Exception e) {
        log.error("An unexpected error occurred:", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    /*
        Setting up an exception handler like this can look complicated, but it can be quite easy if you take it step by step:
         - Without the exception handler, you'll see the specific exception type in the logs (or in the response if you don't have the generic exception handler).
         - Then you can add an exception handler for the exception, with a placeholder return value.
         - Set a breakpoint and investigate how the exception looks and how you can extract all the information you need.
         - Set up your response and return it in a ResponseEntity.
         Note that you should use the appropriate response status code. e.g. 200 success, 404 not found, 400 bad request, 500 internal server error, ...
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleConstraintViolationException(final ConstraintViolationException e) {
        log.warn("A ConstraintViolationException occurred: {}", e.getMessage());
        final ConstraintViolation<?> constraintViolation = e.getConstraintViolations().iterator().next();
        return ResponseEntity
                .badRequest()
                .body(new ValidationError("Field validation failed", constraintViolation.getPropertyPath().toString(), constraintViolation.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(final MethodArgumentNotValidException e) {
        log.error("A MethodArgumentNotValidException occurred: {}", e.getMessage());
        final FieldError fieldError = e.getBindingResult().getFieldError();
        return ResponseEntity
                .badRequest()
                .body(new ValidationError("Field validation failed", fieldError.getField(), fieldError.getDefaultMessage()));
    }

    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<?> handleStudentNotFoundException(final StudentNotFoundException e) {
        log.warn("Encountered a request for a non existing student. id: {}", e.getId());
        return ResponseEntity
                .badRequest()
                .body(new ResourceNotFoundError("There exists no student with id: " + e.getId()));
    }

}
