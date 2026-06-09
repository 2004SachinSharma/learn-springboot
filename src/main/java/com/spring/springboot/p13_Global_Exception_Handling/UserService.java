package com.spring.springboot.p13_Global_Exception_Handling;

import com.spring.springboot.p13_Global_Exception_Handling.Exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public String getUserById(String id) {
        if ("1".equals(id)) {
            return "John Doe";
        } else {
            // This will be handled by the Global Exception Handler
            throw new ResourceNotFoundException("User with ID " + id + " not found");
        }
    }
}
