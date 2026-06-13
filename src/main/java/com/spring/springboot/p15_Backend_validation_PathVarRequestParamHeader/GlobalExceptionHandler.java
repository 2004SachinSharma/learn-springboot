    package com.spring.springboot.p15_Backend_validation_PathVarRequestParamHeader;


    import org.hibernate.boot.MappingNotFoundException;
    import org.hibernate.exception.ConstraintViolationException;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.MissingPathVariableException;
    import org.springframework.web.bind.MissingRequestHeaderException;
    import org.springframework.web.bind.MissingServletRequestParameterException;
    import org.springframework.web.bind.annotation.ExceptionHandler;
    import org.springframework.web.bind.annotation.RestControllerAdvice;
    import org.springframework.web.context.request.WebRequest;
    import org.springframework.web.method.annotation.HandlerMethodValidationException;
    import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

    import java.time.LocalDateTime;

    @RestControllerAdvice
    public class GlobalExceptionHandler {

        /*
           Wait, why we need these Exception Handlers?
           What are they? -> These are like a safety net for our API. They catch mistakes in the request before they reach our business logic.
           Why we need them? -> To give clear, friendly feedback to the user/developer instead of a scary 500 Internal Server Error or a messy stack trace.
           What if they were not there? -> The API would return a default Spring error page or raw exception details, which are hard to debug and look unprofessional.
        */

    //        In the current design of these Exception handlers, the XSS (Cross Site Scripting) vulnerability is there... will learn to improve this later  


        @ExceptionHandler(MissingRequestHeaderException.class)
        public ResponseEntity<ErrorResponseDto> handlerRequestHeaderException(MissingRequestHeaderException exception, WebRequest request) {
            // This handler catches cases where a required header is completely missing from the request.
            return ResponseEntity.badRequest().body(new ErrorResponseDto(LocalDateTime.now(), HttpStatus.BAD_REQUEST, "NoRequestHeaderPresent", "No header '" + exception.getHeaderName() + "' being received from the client", request.getDescription(false)));
        }

        @ExceptionHandler(MethodArgumentTypeMismatchException.class)
        public ResponseEntity<ErrorResponseDto> handlerTypeMismatchException(MethodArgumentTypeMismatchException exception, WebRequest request) {
            // This handler catches cases where the data type sent doesn't match the expected type (e.g., sending text for a number).
            return ResponseEntity.badRequest().body(new ErrorResponseDto(LocalDateTime.now(), HttpStatus.BAD_REQUEST, "ParameterTypeMismatched", "parameter type mismatched (path variable, header or query param), kindly ensure passing suitable type", request.getDescription(false)));
        }

        @ExceptionHandler(MissingPathVariableException.class)
        public ResponseEntity<ErrorResponseDto> handlerMissingPathVariableException(MissingPathVariableException exception, WebRequest request) {
            // This handler catches cases where a required path variable is missing in the URL.
            return ResponseEntity.badRequest().body(new ErrorResponseDto(LocalDateTime.now(), HttpStatus.BAD_REQUEST, "pathVariableMissing", "path variable missing", request.getDescription(false)));
        }

        @ExceptionHandler(MissingServletRequestParameterException.class)
        public ResponseEntity<ErrorResponseDto> handlerMissingServletRequestParameterException(MissingServletRequestParameterException exception, WebRequest request) {
            // This handler catches cases where a required query parameter is missing in the URL.
            return ResponseEntity.badRequest().body(new ErrorResponseDto(LocalDateTime.now(), HttpStatus.BAD_REQUEST, "queryParameterMissing", "Query parameter '" + exception.getParameterName() + "' is missing", request.getDescription(false)));
        }


        @ExceptionHandler(Exception.class) //here HandlerMethodValidationException.class should be there but its not working as expected! So used this generic Exception class to handle any Exception that couldn't be handled above
        public ResponseEntity<ErrorResponseDto> handlerMethodValidationException(Exception exception, WebRequest request) {
            // This handler catches validation errors in @PathVariable, @RequestParam, or @RequestHeader (e.g., @Size or @NotBlank).
            return ResponseEntity.badRequest().body(new ErrorResponseDto(LocalDateTime.now(), HttpStatus.BAD_REQUEST, "HandlerMethodValidationException", "kindly check your request header, path variable or query parameter, you either have sent the blank String or the max or min limit might be exceeding, kindly ensure sending proper value", request.getDescription(false)));
        }
//        will later explore better approach of formatting it, its also fine... but more ways exist

    }
