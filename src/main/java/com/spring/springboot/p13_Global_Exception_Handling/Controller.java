package com.spring.springboot.p13_Global_Exception_Handling;

import com.spring.springboot.p13_Global_Exception_Handling.Exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestController
public class Controller {

    @Autowired
    private UserService userService;

    @GetMapping("/user/{id}")
    public String getUser(@PathVariable String id) {
        return userService.getUserById(id);
    }



    /**
     *
     * This is an Exception handling from the Controller but not the GlobalException Handling from the separate Global Exception Handler class+method,
     * Generally this Controller way is not Recommended, but the classic global-Exception handling way is.
     * Controller-level exception has High Priority to response the Exception compared to the Global Exception Handler.
     */

/*@ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handlerException(Exception exception, WebRequest webRequest) {

//        passing webRequest.getDescription(false) in the ErrorResponseDto may trigger to XSS, SO I printed them instead of returning the path at which the exception si triggered

        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Resource Not Found From Controller",
                LocalDateTime.now()
        );

        System.err.println("Request failed" + exception.getMessage());
        System.err.println("Error at path" + webRequest.getDescription(false));

        return new ResponseEntity<>(
                errorResponseDto,
                HttpStatus.NOT_FOUND
        );

    }*/
}
