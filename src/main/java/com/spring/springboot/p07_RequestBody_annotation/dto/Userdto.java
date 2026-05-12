package com.spring.springboot.p07_RequestBody_annotation.dto;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

public record Userdto( //Here we mandatorily have to use the same names of the fields as the exact key_name in the JSON request body to extract data from the request body
                       String name,
                       String email,
                       String DOB,
                       String sex
){}

/**
 * Records were introduced to create immutable data-carrying classes with very less boilerplate code.
 * Compiler automatically generates:
 * constructor
 * getters/accessors
 * toString()
 * equals()
 * hashCode()
 *
 * Example:
 *
 * record User(String name, String email) {}
 *
 * instead of writing full traditional class.
 *
 * Core Principle
 *
 * “Model pure data as immutable objects.”
 *
 * Benefits
 * Less code
 * Cleaner APIs
 * Immutable by default
 * Thread-safe
 * Better for DTOs and APIs
 * Best Use Cases
 * Request DTO
 * Response DTO
 * API payloads
 * Read-only data objects
 * Not Ideal For
 * JPA Entities
 * Mutable business objects
 * Inheritance-heavy classes*/