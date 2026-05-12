package com.spring.springboot.p08_RequestEntity_class;

import com.spring.springboot.p07_RequestBody_annotation.dto.Userdto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.*;

@RestController
@RequestMapping("/api")
public class RequestController {
/*

1) What is RequestEntity?

RequestEntity<T> is a Spring Boot class used to access
the COMPLETE HTTP request in a single object.

It can contain:
- Request Body
- Request Headers
- URL
- Query Parameters
- HTTP Method

--------------------------------------------------
*/

//2) Example

@PostMapping("/request-entity")
public String createUserWithRequestEntity(
        RequestEntity<Userdto> requestEntity) {

    HttpHeaders headers = requestEntity.getHeaders();

    Userdto userDto = requestEntity.getBody(); //In body and in query parameters we can simply see that all the key:value pairs are extracting out in a straight String of pairs, now this becomes a cumbersome process to extract each key:value manually


    String queryParam = requestEntity.getUrl().getQuery(); //This process is a very cumbersome process to do because of manual extraction of each key:value. Instead these two the specified annotations are recommended to use i.e. @RequestBody and @RequestParam

    String path = requestEntity.getUrl().getPath(); //Returns URI of the current method

    return "Userdto: "+  userDto.toString() + "\n" +
            "RequestHeader: "+headers+ "\n" +
            "QueryParameters: "+queryParam+ "\n" +
            "RequestPath: "+path;
}

//--------------------------------------------------

/*
3) Internal Working

Spring internally:
1. Receives HTTP request
2. Creates RequestEntity object
3. Fills:
   - headers
   - body
   - URL information
4. Injects it into method parameter

--------------------------------------------------

4) Important Methods

requestEntity.getBody()
-> Returns request body

requestEntity.getHeaders()
-> Returns all request headers

requestEntity.getMethod()
-> Returns HTTP method (GET, POST etc.)

requestEntity.getUrl()
-> Returns complete URL

requestEntity.getUrl().getQuery()
-> Returns query parameters

requestEntity.getUrl().getPath()
-> Returns URL path

--------------------------------------------------

5) Significance / Purpose

Normally we use separately:

@RequestBody
@RequestParam
@RequestHeader
@PathVariable

But RequestEntity gives everything together
inside one object.

Useful for:
- Request inspection
- Logging
- Debugging
- Security checks
- API gateway logic
- Middleware type processing

--------------------------------------------------

6) Industry Usage

Most commonly used approach:

@RequestBody
@RequestParam
@RequestHeader

because it is cleaner and easier to read.

RequestEntity is used more in:
- Advanced backend systems
- Internal frameworks
- Security/auth logic
- Proxy/API gateway systems
- Generic request handling

--------------------------------------------------

7) Difference Between @RequestBody and RequestEntity

@RequestBody
- Gives only request body
- Simpler
- Most commonly used

RequestEntity
- Gives complete HTTP request
- More powerful
- Used in special situations

--------------------------------------------------

8) Similar Class

ResponseEntity
-> Used to control complete HTTP response

Very heavily used in industry.

Example:

return ResponseEntity.ok(user);

--------------------------------------------------

9) Interview One-Line Definition

RequestEntity represents the complete incoming
HTTP request including:
- body
- headers
- method
- URL details

========================================
*/
}
