package com.inventory.exception;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorMessage<Violation>> onConstraintValidationException(ConstraintViolationException e) {
        List<Violation> errors = new ArrayList<>();
        for (ConstraintViolation<?> violation : e.getConstraintViolations()) {
            errors.add(new Violation(violation.getPropertyPath().toString(), violation.getMessage()));
        }

        ErrorMessage<Violation> message = new ErrorMessage<>(
                HttpStatus.BAD_REQUEST.value(),
                new Date(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                errors);

        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ErrorMessage<Violation>> onMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<Violation> errors = new ArrayList<>();
        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            errors.add(new Violation(fieldError.getField(), fieldError.getDefaultMessage()));
        }

        ErrorMessage<Violation> message = new ErrorMessage<>(
                HttpStatus.BAD_REQUEST.value(),
                new Date(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                errors);

        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<ErrorMessage<ErrorMessage.Error>> resourceNotFoundException(ResourceNotFoundException ex) {
        List<ErrorMessage.Error> errors = List.of(new ErrorMessage.Error("123", ex.getMessage()));
        ErrorMessage<ErrorMessage.Error> message = new ErrorMessage<>(
                HttpStatus.NOT_FOUND.value(),
                new Date(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                errors);

        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({ResourceExistsException.class})
    public ResponseEntity<ErrorMessage<ErrorMessage.Error>> resourceExistsException(ResourceExistsException ex) {
        List<ErrorMessage.Error> errors = List.of(new ErrorMessage.Error("000", ex.getMessage()));
        ErrorMessage<ErrorMessage.Error> message = new ErrorMessage<>(
                HttpStatus.BAD_REQUEST.value(),
                new Date(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                errors);

        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage<ErrorMessage.Error>> globalExceptionHandler(Exception ex, WebRequest request) {
        List<ErrorMessage.Error> errors = List.of(new ErrorMessage.Error("456", ex.getMessage()));
        ErrorMessage<ErrorMessage.Error> message = new ErrorMessage<>(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                new Date(),
                ex.getMessage(),
                errors);

        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
