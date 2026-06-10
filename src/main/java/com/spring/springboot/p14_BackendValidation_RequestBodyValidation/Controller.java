package com.spring.springboot.p14_BackendValidation_RequestBodyValidation;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class Controller {

    ServiceImpl service;

    public Controller(ServiceImpl service) {
        this.service = service;
    }

    @PostMapping("/user/v1")
    public void addUser( @Valid @RequestBody  UserDto userdto) { //Here  @Valid annotation ensures that before parsing the JSON to java Dto Objects, the validation must be enforced on fields throw the validations we have defined in the Dto i.e. @NotBlank, @Size() etc...
        service.addUser(userdto);
    }

    @GetMapping("/user/v1")
    public ResponseEntity<Map<String, Object>> getUser(){


     return  ResponseEntity.status(HttpStatus.OK).body(service.getUser());

    }


}
