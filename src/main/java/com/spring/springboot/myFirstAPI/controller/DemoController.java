package com.spring.springboot.myFirstAPI.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @GetMapping("/home")
    public String demo(){
        return "Hey keep it up! Sachin";
    } //on http://localhost:8080/home

}

//See this is just a demo of how easily the springboot helps to create API
//Rest all annotations used here will be covered further, do not worry!