package group40.newsapp.controller;

import group40.newsapp.DTO.ResponseErrors;
import group40.newsapp.exception.NullArgException;
import group40.newsapp.exception.RestException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RestException.class)
    public ResponseEntity<String> handlerNotFoundException(RestException exception) {
        return new ResponseEntity<>(exception.getMessage(), exception.getStatus());
    }

    @ExceptionHandler(NullArgException.class)
    public ResponseEntity<String> handlerNullArgException(NullArgException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> handlerConstraintViolationException(ConstraintViolationException exception) {

        StringBuilder responseMessage = new StringBuilder();

        exception.getConstraintViolations()
                .forEach(constraintViolation -> {
                    String message = constraintViolation.getMessage();
                    responseMessage.append(message);
                    responseMessage.append("\n");
                });
        return new ResponseEntity<>(responseMessage.toString(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ResponseErrors>> handlerMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        final List<ResponseErrors> errors = e.getBindingResult().getFieldErrors().stream()
                .map(error -> new ResponseErrors(error.getField(), error.getDefaultMessage()))
                .toList();
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}