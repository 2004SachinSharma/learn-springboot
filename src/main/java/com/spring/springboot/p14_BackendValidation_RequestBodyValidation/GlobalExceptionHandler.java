package com.spring.springboot.p14_BackendValidation_RequestBodyValidation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;


@RestControllerAdvice
public class GlobalExceptionHandler {

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<ErrorResponseDto> handlerMethodArgumentNotValid(MethodArgumentNotValidException exception) {
//
//
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseDto(HttpStatus.BAD_REQUEST, exception.getMessage(), LocalDateTime.now()));
//
//    }

    //    Use either of the Above one or the below one
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handlerMethodArgumentNotValid(MethodArgumentNotValidException exception){
        Map<String,String> errors = new HashMap<>();

        exception.getBindingResult().getFieldErrors().forEach((fieldError) -> { errors.put(fieldError.getField(),fieldError.getDefaultMessage());});//The best way to return User friendly error messages


        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);

    }


}
