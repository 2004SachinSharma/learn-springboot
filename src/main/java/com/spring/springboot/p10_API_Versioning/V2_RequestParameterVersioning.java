package com.spring.springboot.p10_API_Versioning;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class V2_RequestParameterVersioning {

    //        2. Request Parameter Versioning

//    Version passed as a query parameter.

//            Example:

//            /api/users?version=1
//            /api/users?version=2

        @GetMapping(value = "/users", params = "version=1")
        public ResponseEntity<String> getUsersV1() {
            return ResponseEntity.ok("Users from V1");
        }

        @GetMapping(value = "/users", params = "version=2")
        public ResponseEntity<String> getUsersV2() {
            return ResponseEntity.ok("Users from V2");
        }
    }
//    URLs
//    http://localhost:8080/api/users?version=1
//    http://localhost:8080/api/users?version=2

//    Internally What Happens?

/*
    Spring checks:

    params = "version=1"

    If request parameter matches:

            ?version=1

    then that method executes.
*/

