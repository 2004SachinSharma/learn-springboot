package com.spring.springboot.p05_RequestParam_annotation;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/request")
public class RequestController {
//In @RequestParam, you will notice attributes of this annotation work the same as the @RequestMapping annotation
   private String username = "admin";
   private String password = "admin";

    //See below the way to extract out the queryParameter from the URI (Focus on @RequestParam)
    @PostMapping("/setuser/{adminAuth}/{password}")
    String setUserName(@PathVariable String adminAuth,
                       @PathVariable String password,
                       @RequestParam String username){ //@RequestParam annotation is used to extract out the query parameter from the URL Or URI(Specifically)
       //This is mandatory that the parameter name accepting the corresponding query parameter, both must have the same name as it's given in the url.
       //Although we have the name attribute as we have seen in @RequestMapping(name ="..."). Similarly we have here see above

       //In the anatomy of the HTTP Request the query-parameters always come after the '?'
//       for e.g. HTTP GET path/ {path - variable}?user=xa3q12ax&gender="male" and so on
//                                                |-----query-parameters-----|



        if(!adminAuth.equals("Sachin") || !password.equals("1234")){
            return "New admin name could not be updated!";
        }
        
        this.username=username;
        return this.username + " New admin update successful!";
    }


    //In the below method look at how we can make queryParameters optional
    @PostMapping("/setdatabase")
    String setDatabase(
            @RequestParam(required = false, defaultValue = "MySQL") String database //This is the way, how we can make queryParameter passing optional to pass, and instead of that queryParameter value, we can map the parameter variable to some default value using 'defaultValue attribute', in the case the query parameter is not passed
    ){
       return "Successfully Connected with: "+ database;
    }

    //Below, Handler shows how we can map the key name to the different param name in the method space
    @GetMapping("/setusergender")
    String setUserInfo(
            @RequestParam(name = "gender") String sex
    ){
       String isValidGender;

       if(sex.equalsIgnoreCase("Male") || sex.equalsIgnoreCase("Female")|| sex.equalsIgnoreCase("Others")){
           isValidGender = "The user is registered as "+ sex;
       }else{
           isValidGender = "Sorry not a valid gender, kindly check and re-submit :)";
       }
        return isValidGender;
   }

   //Avoiding that manual overhead of putting @RequestParam for each query-parameter, Instead using Map<>
   @PostMapping("/saveUsers")
    String saveUsers(@RequestParam Map<String, String> users){
       users.forEach((k,v)->{
           System.out.println(k+": "+v);
       });

       return "Successfully saved users: " + users.get("user1") +","+ users.get("user2") +","+ users.get("user3");

   }



}
