package com.spring.springboot.PathVariable_annotation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/api/dummy/users/{userId}/post/{postId}")
    public String searchUserPostWithMultiPathVariable(@PathVariable Long userId, @PathVariable   Long postId){
        System.out.printf("Returned user and its post is %d and %d respectively",userId, postId, userId, postId );

        return "Returned user and its post is at "+userId+" and "+postId+" respectively";
    }

}
