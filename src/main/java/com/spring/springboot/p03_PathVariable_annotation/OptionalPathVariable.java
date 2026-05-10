package com.spring.springboot.pathvariable_annotation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OptionalPathVariable {

    /*
        =========================================================
                    INDUSTRY STANDARD APPROACH
        =========================================================

        Separate endpoints are generally preferred in enterprise
        REST API design.

        Why?

        Because PathVariables represent the structure and identity
        of a resource itself.

        Examples:
            /api/users
            /api/users/101

        These URLs clearly represent different resources.

        Benefits of separate methods:
        --------------------------------
        1. Cleaner REST API design
        2. Better readability
        3. Better Swagger/OpenAPI documentation
        4. Easier frontend integration
        5. Easier testing and maintenance
        6. Less ambiguity in API behavior
        7. Better scalability for future changes

     */



    // =========================================================
    // GOOD PRACTICE
    // =========================================================

    // GET /api/users
    @GetMapping("/api/users")
    public String getAllUsers() {

        System.out.println("Fetching all users");

        return "Fetching all users";
    }



    // GET /api/users/101
    @GetMapping("/api/users/{userId}")
    public String getUserById(
            @PathVariable Long userId
    ) {

        System.out.println(
                "Fetching user with id = " + userId
        );

        return "Fetching user with id = " + userId;
    }






    /*
        =========================================================
                    NOT A GOOD PRACTICE
        =========================================================

        Trying to make PathVariable optional unnecessarily.

        Example:
            /api/users
            /api/users/101

        handled using SAME controller method.

        Why this is generally NOT preferred:
        -------------------------------------

        1. URL structure becomes ambiguous
           API behavior becomes less explicit.

        2. Harder to understand for frontend teams.

        3. Swagger/OpenAPI documentation may become confusing.

        4. Controller logic starts handling multiple responsibilities.

        5. Becomes harder to maintain as project grows.

        6. Different resources should ideally have different endpoints.

        IMPORTANT:
        -------------------------------------
        The optional behavior works mainly because of:
            multiple URL mappings

        and NOT only because of:
            required = false

        Without:
            "/api/bad/users"

        Spring would never match the request when
        userId is absent.
     */


    // =========================================================
    // GENERALLY NOT RECOMMENDED
    // =========================================================

    @GetMapping({
            "/api/bad/users/{userId}",
            "/api/bad/users"
    })
    public String badPracticeExample(
            @PathVariable(required = false) Long userId
    ) {

        if (userId != null) {

            System.out.println(
                    "Fetching single user with id = " + userId
            );

            return "Fetching single user with id = " + userId;
        }

        System.out.println("Fetching all users");

        return "Fetching all users";
    }
}