package com.spring.springboot.p10_API_Versioning;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
API Versioning Notes (Spring Boot)

API versioning means managing changes in APIs without breaking old clients/apps.

        Real-world example:
Imagine Instagram
changes the response of /users suddenly. Old mobile apps may crash.
So companies create versions like:

        /api/v1/users
/api/v2/users

This allows old apps and new apps to work together.

Why API Versioning Exists

Without versioning:

Mobile apps break
Frontend crashes
Third-party integrations fail
Old clients stop working

Large companies like Google
                , Microsoft
                , Stripe
heavily use versioning.
        */

//When Do We Need Versioning?

//Suppose initially:

/** {
 "name":"Sachin",
 "age":20
 }
 */

//Later company changes API:

        /*{
        "fullName":"Sachin Sharma",
        "age":20,
        "college":"RJIT"
        }*/

//Old frontend expecting name may fail.

//So instead of modifying old API directly:

/*
Keep old API → v1
Create new API → v2
Types of API Versioning
*/

//Mainly 4 types:

/*URI Versioning (MOST COMMON)
Request Parameter Versioning
Header Versioning
Media Type Versioning
*/

//1. URI Versioning (Industry Favorite)

//Example:

/*  /api/v1/users
    /api/v2/users
         */



@RestController
@RequestMapping("/api")
public class V1_URIVersioning {

    @GetMapping("/v1/users")
    public ResponseEntity<String> getUsersV1() {
        return ResponseEntity.ok("API Version 1.0.0");
    }

    @GetMapping("/v2/users")
    public ResponseEntity<String> getUsersV2() {
        return ResponseEntity.ok("API Version 2.0.0");
    }
}

//URL Testing
//http://localhost:8080/api/v1/users
//http://localhost:8080/api/v2/users

//Why URI Versioning Is Popular

//Because:

/*
Simple
Easy to understand
Easy debugging
Frontend friendly
Visible directly in URL
*/

//That’s why most startups and service companies use this.

//Even many microservices internally follow this.




