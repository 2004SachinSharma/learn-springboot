package com.spring.springboot.p13_Global_Exception_Handling.Exception;

//As all the Exceptions are not provided by the Java or spring, so we should create our own custom exceptions according to the business requirement.

public class ResourceNotFoundException extends RuntimeException{
public ResourceNotFoundException(String message){
    super( message);
}
}
