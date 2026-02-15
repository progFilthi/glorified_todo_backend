package com.product.glorifiedtodo.exceptions;

import com.product.glorifiedtodo.dtos.ApiErrorResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponseDto> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex,
                                                                                     HttpServletRequest request) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(err ->
                errors.put(err.getField(), err.getDefaultMessage())
        );

        ApiErrorResponseDto response = new ApiErrorResponseDto(
                "Validation failed",
                request.getRequestURI(),
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now(),
                errors
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<ApiErrorResponseDto> handleResourceNotFound(ResourceNotFound ex, HttpServletRequest request) {

        String message = ex.getMessage();

        ApiErrorResponseDto response = new ApiErrorResponseDto(
                message,
                request.getRequestURI(),
                HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now(),
                null
        );


        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(ResourceConflict.class)
    public ResponseEntity<ApiErrorResponseDto> handleResourceConflict(ResourceConflict ex, HttpServletRequest request) {

        String message = ex.getMessage();

        ApiErrorResponseDto response = new ApiErrorResponseDto(
                message,
                request.getRequestURI(),
                HttpStatus.CONFLICT.value(),
                LocalDateTime.now(),
                null
        );


        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }




    //generic exception -> 500 internal server errors
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponseDto> handleGenericException(Exception ex, HttpServletRequest request) {

        String message = ex.getMessage();

        ApiErrorResponseDto response = new ApiErrorResponseDto(
                message,
                request.getRequestURI(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                LocalDateTime.now(),
                null
        );


        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
