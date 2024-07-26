package group40.newsapp.controller;

import group40.newsapp.DTO.errorDTO.ErrorResponseDto;
import group40.newsapp.DTO.errorDTO.FieldErrorDto;
import group40.newsapp.DTO.errorDTO.ResponseErrors;
import group40.newsapp.DTO.validationErrorDto.ValidationErrorDto;
import group40.newsapp.DTO.validationErrorDto.ValidationErrorsDto;
import group40.newsapp.exception.AlreadyExistException;
import group40.newsapp.exception.NotFoundException;
import group40.newsapp.exception.NullArgException;
import group40.newsapp.exception.RestException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handlerNotFoundException(NotFoundException exception){
        ErrorResponseDto errorResponse = ErrorResponseDto.builder()
                .message(exception.getMessage())
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RestException.class)
    public ResponseEntity<ErrorResponseDto> handlerNotFoundException(RestException exception) {
        ErrorResponseDto errorResponse = ErrorResponseDto.builder()
                .message(exception.getMessage())
                .build();
        return new ResponseEntity<>(errorResponse, exception.getStatus());
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ErrorResponseDto> handlerNullPointerException(NullPointerException exception){
        ErrorResponseDto errorResponse = ErrorResponseDto.builder()
                .message(exception.getMessage())
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(AlreadyExistException.class)
    public ResponseEntity<ErrorResponseDto> handlerAlreadyExistException(AlreadyExistException exception){
        ErrorResponseDto errorResponse = ErrorResponseDto.builder()
                .message(exception.getMessage())
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NullArgException.class)
    public ResponseEntity<String> handlerNullArgException(NullArgException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponseDto> handlerConstraintViolationException(ConstraintViolationException exception){
        List<FieldErrorDto> fieldErrors = new ArrayList<>();

        exception.getConstraintViolations().forEach(violation -> {
            FieldErrorDto fieldError = FieldErrorDto.builder()
                    .field(violation.getPropertyPath().toString())
                    .message(violation.getMessage())
                    .rejectedValue(violation.getInvalidValue())
                    .build();
            fieldErrors.add(fieldError);
        });

        ErrorResponseDto errorResponse = ErrorResponseDto.builder()
                .message("Validation failed")
                .fieldErrors(fieldErrors)
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorsDto> handleValidationException(MethodArgumentNotValidException e) {

        List<ValidationErrorDto> validationErrors = new ArrayList<>();

        List<ObjectError> errors = e.getBindingResult().getAllErrors();

        for (ObjectError error : errors) {
            FieldError fieldError = (FieldError)error;
            ValidationErrorDto errorDto = ValidationErrorDto.builder()
                    .field(fieldError.getField())
                    .message(fieldError.getDefaultMessage())
                    .build();

            if (fieldError.getRejectedValue() != null) {
                errorDto.setRejectedValue(fieldError.getRejectedValue().toString());
            }

            validationErrors.add(errorDto);
        }

        return ResponseEntity
                .badRequest()
                .body(ValidationErrorsDto.builder()
                        .errors(validationErrors)
                        .build());
    }
}