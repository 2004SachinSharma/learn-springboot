package com.spring.springboot.p11_API_Versioning_latestSpring7Approach.ControllerforAll4Versioning;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//Same as requestParamterVersioning, now we need not to change anything in this file but WebConfig is only the place to switch over the various versioning type
@RestController
@RequestMapping("/api/version")
public class VersionController {

    @GetMapping(version = "1")
    ResponseEntity<String> defaultAPIversion(){
     return ResponseEntity.ok("API Version 1.0.0");
    }

    @GetMapping(version = "2")
    ResponseEntity<String> v2Version(){
        return ResponseEntity.ok("API Version 2.0.0");
    }

}
