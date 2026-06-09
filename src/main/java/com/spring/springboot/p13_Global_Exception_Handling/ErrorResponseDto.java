package com.spring.springboot.p13_Global_Exception_Handling;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public record ErrorResponseDto(HttpStatus ErrorCode, String errorMessage, LocalDateTime errorTime) {

}
