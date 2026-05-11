package com.spring.springboot.p06_RequestHeader_annotation;

import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



/**
 * @RequestHeader is used to extract out the data from the request header data like location, language, etc
 * It's same as using {@RequestParam}, same as it, from header also the values can be extracted out in the key:value format. See below */

@RestController
@RequestMapping("/user")
public class RequestController {

    @GetMapping("/headerData")
String header(@RequestHeader(name = "Connection") String connection, @RequestHeader(name = "User-Agent") String userAgent) {
    return "From the Request Header of this request from Postman\n connection is: " +connection  + "\n userAgent is: " + userAgent;
}

    @GetMapping("/location")
    String getLocation(@RequestHeader(name = "Location") String location){
        return "The Current Location is: " + location;
}

//Another way for extracting data, using HttpHeaders class, more flexible inbuilt way to extract data
@GetMapping("/http-header")
    String readingRequestHeaderFromHttpHeaders(@RequestHeader HttpHeaders httpHeaders){
       return ""+ httpHeaders.get("User-Agent") +" " + httpHeaders.get("Connection") + " " + httpHeaders.get("Location");
}
//And rest; all the attributes are present here also in @RequestHeader whichever we have seen in the @RequestParam like optional, default, value passing and using Map<...,...> for N no. of key: value pairs in request Header


}
