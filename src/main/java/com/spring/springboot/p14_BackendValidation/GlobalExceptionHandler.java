package com.spring.springboot.p14_BackendValidation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDto> handlerMethodArgumentNotValid(MethodArgumentNotValidException exception) {


        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseDto(HttpStatus.BAD_REQUEST, exception.getMessage(), LocalDateTime.now()));

    }

}
