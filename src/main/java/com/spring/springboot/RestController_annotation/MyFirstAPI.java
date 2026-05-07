package com.spring.springboot.RestController_annotation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.webmvc.autoconfigure.error.ErrorMvcAutoConfiguration;

@SpringBootApplication/*(exclude = {ErrorMvcAutoConfiguration.class})*/ //In this way we can also exclude some beans from not being created by IoC, now we can see in the logs in the services tab at the left-down side, the Exclusion list is getting populated with this excluded class
//Generally this is discouraged to exclude anything, as Springboot loads something with some reason only. And only in some advance scenarios, or in rare scenarios, exclusion is done

public class MyFirstAPI {

    private static final Logger log = LoggerFactory.getLogger(MyFirstAPI.class);

    static void main(String[] args) {
        SpringApplication.run(MyFirstAPI.class);

    }

}
