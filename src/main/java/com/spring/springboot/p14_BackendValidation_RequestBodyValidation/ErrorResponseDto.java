package com.spring.springboot.p14_BackendValidation_RequestBodyValidation;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public record ErrorResponseDto(HttpStatus ResponseStatus, String errorMessage, LocalDateTime timestamp) {
}
