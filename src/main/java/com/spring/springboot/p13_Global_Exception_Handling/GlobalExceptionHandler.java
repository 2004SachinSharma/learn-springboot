package com.spring.springboot.p13_Global_Exception_Handling;

import com.spring.springboot.p13_Global_Exception_Handling.Exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

//@RestControllerAdvice This is the annotation for declaring this class as the Global Exception handler class
public class GlobalExceptionHandler {


    @ExceptionHandler(ResourceNotFoundException.class)  //This annotation is used on methods to tell which method should be executed for a specific type of the exception! Like here for ResourceNotFoundException this method should run.
    public ResponseEntity<ErrorResponseDto> handlerException(Exception exception, WebRequest webRequest) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Resource Not Found From Global Exception Handling",
                LocalDateTime.now()
        );

//       Map<String,Object> ErrorResponse = new HashMap<>();
//       ErrorResponse.put("ErrorCode", HttpStatus.INTERNAL_SERVER_ERROR.value());
//       ErrorResponse.put("ErrorMessage", exception.getMessage());
//       ErrorResponse.put("errorTime",  LocalDateTime.now()); // We can use Map as well inplace of ErrorResponseDto(s) but not recommended because using DTOs we get proper contract, security and other more benefits


//        passing webRequest.getDescription(false) in the ErrorResponseDto may trigger to XSS, SO I printed them instead of returning the path at which the exception si triggered
        System.err.println("Request failed" + exception.getMessage());
        System.err.println("Error at path" + webRequest.getDescription(false));

        return new ResponseEntity<>(
                errorResponseDto,
                HttpStatus.NOT_FOUND
        );
    }


//    similarly, we can throw more exceptions and catch them in these Global Exception handlers by making more handlers ro handle specific exceptions  like:

    public ResponseEntity<ErrorResponseDto> handlerException2(Exception exception, WebRequest webRequest) {

//    the logic goes here
        return null;
    }

    //And so on for other specific exceptions like NullPointer, IOException, etc.


// This below one is the Generic Handler, means when no any specific Exception handler above be matched, this Generic Exception Handler will be executed...
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handlerException3(Exception exception,  WebRequest webRequest) {

//    the logic goes here
        return null;
    }

}
