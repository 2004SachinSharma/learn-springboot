package com.spring.springboot.p03_PathVariable_annotation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UserController {

    //Through this method let's understand how to use PathVariable at the basic level
    @GetMapping("/api/dummy/users/{userId}/post/{postId}")
    public String searchUserPostWithMultiPathVariable(@PathVariable Long userId, @PathVariable Long postId) {
        System.out.printf("Returned user and its post is %d and %d respectively", userId, postId, userId, postId);

        return "Returned user and its post is at " + userId + " and " + postId + " respectively";
    }


    //Through this method let's understand how to use PathVariable with different parameter name and mapping the specific path variable
    //to the corresponding parameter name but with a different name using the name attribute of the @PathVariable annotation

    @GetMapping("/api/dummy/users/post/{contentId}")
    public String searchUserPostWithPathVariable2(@PathVariable(name = "contentId") Long customerId) {

        /** See here the names of the Path Variables, and the parameter is mismatched; it will throw an exception at run time

         Due to this on Browser: The error would come-
         Whitelabel Error Page
         This application has no explicit mapping for /error, so you are seeing this as a fallback.

         Mon May 11 01:17:43 IST 2026
         There was an unexpected error (type=Internal Server Error, status=500).


         and at run time we can see:
         2026-05-11T01:17:43.806+05:30 WARN 17120 --- [springboot] [nio-8080-exec-4] .w.s.m.s.DefaultHandlerExceptionResolver:
         Resolved [org.springframework.web.bind.MissingPathVariableException: Required URI template variable 'customerId'
         for method parameter type Long is not present]


         Now let's move to the solution look above in the parameter space: with
         @PathVariable(name = "contentId") it would now work
         */


        System.out.println("Returned user and its post is at " + customerId );


        return "Returned user and its post is at " + customerId ;
    }

    //Through this method let's understand how to simplify declaring the multiple parameters corresponding to multiple path variables.
    //A Real cumbsum process is this when we have to manually do @PathVariable for all the n no. of path variables, OMG...
    //Isn't there anything like doing one time, and done for all?
    //Answer is Big Yes, that is Map<String, String>

    @GetMapping("/api/dummy/country/{countryId}/user/{userId}/post/{postId}")
    public String searchUserPostWithPathVariable2(@PathVariable Map<String, String> users) { //Here Key would be: path variable name
                                                                                             //value would be the real path variable from the URL

        return "The user is from: "+ users.get("countryId") +" and User ID is: "+ users.get("userId")+ "postID is: "+ users.get("postId");
    }



}
