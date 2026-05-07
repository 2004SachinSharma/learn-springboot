package com.spring.springboot.RestController_annotation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MyFirstAPI {

    private static final Logger log = LoggerFactory.getLogger(MyFirstAPI.class);

    static void main(String[] args) {
        SpringApplication.run(MyFirstAPI.class);

    }

}
