package com.spring.springboot.p14_BackendValidation;

import com.sun.net.httpserver.HttpsServer;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public record ErrorResponseDto(HttpStatus ResponseStatus, String errorMessage, LocalDateTime timestamp) {
}
