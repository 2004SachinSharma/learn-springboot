package com.spring.springboot.p15_Backend_validation_PathVarRequestParamHeader;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
public class Controller {

    /*
       Wait, why we need these Exception Handlers?
       What are they? -> These are like a safety net for our API. They catch mistakes in the request before they reach our business logic.
       Why we need them? -> To give clear, friendly feedback to the user/developer instead of a scary 500 Internal Server Error or a messy stack trace.
       What if they were not there? -> The API would return a default Spring error page or raw exception details, which are hard to debug and look unprofessional.
    */

    //For header
    @PostMapping("/employees")
    public ResponseEntity<String> setHeaderInfo(@RequestHeader
                                                @NotBlank(message = "Seems you sent your secretMessage header empty, Kindly fill some secretMessage in it")
                                                @Size(max = 50, message = "secretMessage limit exceeds, kindly cover your message within 20 characters")
                                                String secretMessage) {

        return ResponseEntity.ok(secretMessage);
    }


    //For pathVariables
    @PostMapping("/employees/{id}")
    public ResponseEntity<String> setPathVariableInfo(@PathVariable
                                                      @NotBlank(message = "Seems you sent your id pathVariable empty, Kindly fill some id in it")
                                                      @Size(max = 3, message = "id limit exceeds, kindly cover your id within 3 characters")
                                                      String id) {

        return ResponseEntity.ok(id);

    }

    //For Query Parameters (Request Parameters)
    @PostMapping("/employees/search")
    public ResponseEntity<String> setRequestParamInfo(@RequestParam
                                                      @NotBlank(message = "Seems you sent your name query parameter empty, Kindly fill some name in it")
                                                      @Size(max = 10, message = "name limit exceeds, kindly cover your name within 10 characters")
                                                      String name) {

        return ResponseEntity.ok(name);
    }


}

