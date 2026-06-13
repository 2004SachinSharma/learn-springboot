package com.spring.springboot.p15_Backend_validation_PathVarRequestParamHeader;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public record ErrorResponseDto(
         LocalDateTime TimeStamp,
         HttpStatus status,
         String error,
         String message,
         String path
){

}
