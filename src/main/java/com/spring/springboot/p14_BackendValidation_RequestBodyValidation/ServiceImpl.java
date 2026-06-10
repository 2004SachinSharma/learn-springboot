package com.spring.springboot.p14_BackendValidation_RequestBodyValidation;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ServiceImpl {

    Map<String,Object> map = new HashMap<>();

    public void addUser(UserDto userDto) {

        map.put("username", userDto.userName());
        map.put("email", userDto.email());
        map.put("message", userDto.message());

    }
    public Map<String, Object> getUser() {

        return map;
    }

}
