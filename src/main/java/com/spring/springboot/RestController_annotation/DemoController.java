package com.spring.springboot.RestController_annotation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @GetMapping("/home")
    public String demo(){
        return "Hey keep it up! Sachin";
    } //on http://localhost:8080/home

}
//See GetMapping() for HTTP/HTTPS's GET requests
