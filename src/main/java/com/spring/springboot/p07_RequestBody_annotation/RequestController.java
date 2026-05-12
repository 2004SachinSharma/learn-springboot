package com.spring.springboot.p07_RequestBody_annotation;

import com.spring.springboot.p07_RequestBody_annotation.dto.Userdto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/request")
public class RequestController {

    @GetMapping("/getrequestbody")
    String getRequestBodyContent(@RequestBody Userdto userdto) {
        return userdto.toString();
    }



}
