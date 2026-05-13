package com.spring.springboot.p10_API_Versioning;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//    3. Header Versioning

@RestController
@RequestMapping("/api")
public class V3_HeaderVersioning {

//    Version sent inside HTTP header.
//    Example Header:
//    X-API-VERSION: 1

        @GetMapping(value = "/users", headers = "X-API-VERSION=1")
        public ResponseEntity<String> getUsersV1() {
            return ResponseEntity.status(HttpStatus.OK).body("Response from API V1");
        }

        @GetMapping(value = "/users", headers = "X-API-VERSION=2")
        public ResponseEntity<String> getUsersV2() {
            return ResponseEntity.ok("Users from V2");
        }
    }
//    Testing in Postman

/*
    Go to:

    Headers tab

    Add:

    Key Value
    X-API-VERSION	1
    Why Companies Use This

    Cleaner URLs.
*/

//    But debugging becomes harder because version is hidden inside headers.

//    Mostly used in enterprise systems.

