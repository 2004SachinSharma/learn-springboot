package com.spring.springboot.p04_RequestMapping_annotation;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



// @RequestMapping can be used both at class level and method level
// It is used to map HTTP requests to handler methods

@RestController
@RequestMapping("/api")   // Base path for all methods inside this controller
class UserController {

    // Handles GET request
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String getUsers() {
        return "Fetching users";
    }

    // Handles POST request
    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public String createUser() {
        return "Creating user";
    }

    // Handles PUT request
    @RequestMapping(value = "/users", method = RequestMethod.PUT)
    public String updateUser() {
        return "Updating user";
    }

    // Handles DELETE request
    @RequestMapping(value = "/users", method = RequestMethod.DELETE)
    public String deleteUser() {
        return "Deleting user";
    }
}

@RestController
class UserController2{

    @RequestMapping("/api2") //See no need to put anything on class level annotation if there is only one handler
    String users(){
        return "Fetching users";
    }
}


/*
    Notes:

    1. Final endpoints become:
            GET     /api/users
            POST    /api/users
            PUT     /api/users
            DELETE  /api/users

    2. If method attribute is not provided:
            @RequestMapping("/users")

       then it accepts ALL HTTP methods.
       Usually not preferred in REST APIs.

    3. Nowadays industry mostly uses:
            @GetMapping
            @PostMapping
            @PutMapping
            @DeleteMapping

       because they are shorter and more readable.

    4. @RequestMapping is parent annotation of all these mapping annotations.

*/