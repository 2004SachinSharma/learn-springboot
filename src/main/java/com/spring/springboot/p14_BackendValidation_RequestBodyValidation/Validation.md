# Spring Boot Validation Notes

## What is Validation?

Validation is the process of checking whether incoming client data follows predefined rules before processing it.

### Example Request

```json
{
  "userName": "",
  "email": "abc"
}
```

### Problems

* Username is blank.
* Email format is invalid.

Validation catches these issues before data reaches the Service Layer or Database Layer.

---

## Why Validation?

Without validation, developers would need to manually write checks everywhere.

### Without Validation

```java
if(userName == null || userName.trim().isEmpty()){
    throw new IllegalArgumentException("Invalid username");
}
```

```java
if(email == null || !email.contains("@")){
    throw new IllegalArgumentException("Invalid email");
}
```

### Problems with Manual Validation

* Repetitive code
* Difficult to maintain
* Error-prone
* Inconsistent validation logic

Validation annotations eliminate this boilerplate code.

---

## Dependency Required

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-validation</artifactId>
</dependency>
```

Without this dependency:

```java
@NotBlank
@Email
@Size
```

will not work.

---

# Common Validation Annotations

## @NotNull

```java
@NotNull
String name;
```

### Rejects

```java
null
```

### Allows

```java
""
"   "
```

### Use Case

When the value must not be null.

---

## @NotEmpty

```java
@NotEmpty
String name;
```

### Rejects

```java
null
""
```

### Allows

```java
"   "
```

### Use Case

When the value must contain at least one character.

---

## @NotBlank

```java
@NotBlank
String name;
```

### Rejects

```java
null
""
"   "
```

### Use Case

Most commonly used for String fields.

---

## @Size

```java
@Size(min = 2, max = 20)
String userName;
```

### Use Case

Controls String length.

### Example

```java
@Size(min = 2, max = 20)
```

Allows:

```text
John
Sachin
```

Rejects:

```text
A
VeryVeryVeryLongUserName
```

---

## @Email

```java
@Email
String email;
```

### Valid

```text
abc@gmail.com
john@test.com
```

### Invalid

```text
abc
gmail.com
hello123
```

---

## @Min

```java
@Min(18)
int age;
```

### Use Case

Minimum allowed numeric value.

---

## @Max

```java
@Max(60)
int age;
```

### Use Case

Maximum allowed numeric value.

---

## @Pattern

```java
@Pattern(regexp = "[A-Z]{5}[0-9]{4}[A-Z]{1}")
String pan;
```

### Use Case

Custom validation using Regular Expressions (Regex).

Examples:

* PAN Number
* Phone Number
* Password Rules
* Aadhaar Format

---

# DTO Validation

Validation is generally applied on DTOs.

```java
public record UserDto(

    @NotBlank
    @Size(min = 2, max = 20)
    String userName,

    @NotBlank
    @Email
    String email

) {}
```

### Why DTO?

```text
Client
  ↓
DTO Validation
  ↓
Service
  ↓
Database
```

Bad data is stopped before entering business logic.

---

# How Spring Triggers Validation

```java
@PostMapping
public ResponseEntity<?> createUser(
        @Valid @RequestBody UserDto userDto) {

}
```

### Important Annotation

```java
@Valid
```

Without `@Valid`:

```text
Validation annotations are ignored.
```

This is one of the most frequently asked interview questions.

---

# Internal Flow of Validation

Request:

```json
{
  "userName": "",
  "email": "abc"
}
```

Flow:

```text
Client
  ↓
Controller
  ↓
@Valid
  ↓
Validation Framework
  ↓
Validation Fails
  ↓
MethodArgumentNotValidException
  ↓
Global Exception Handler
  ↓
400 Bad Request
```

---

# Validation Exception

When validation fails, Spring automatically throws:

```java
MethodArgumentNotValidException
```

### Interview Question

Q: Which exception is thrown when validation fails?

Answer:

```java
MethodArgumentNotValidException
```

---

# Validation with Global Exception Handling

```java
@ExceptionHandler(MethodArgumentNotValidException.class)
public ResponseEntity<?> handleValidationException(
        MethodArgumentNotValidException ex) {

}
```

This allows us to return custom validation error responses.

Example:

```json
{
  "status": 400,
  "message": "Username cannot be blank"
}
```

instead of Spring's default error response.

---

# Validation vs Manual Checks

Without Validation:

```java
if(userName == null || userName.trim().isEmpty()){
    throw new IllegalArgumentException("Invalid username");
}

if(userName.length() < 2 || userName.length() > 20){
    throw new IllegalArgumentException("Invalid username length");
}

if(!email.contains("@")){
    throw new IllegalArgumentException("Invalid email");
}
```

With Validation:

```java
@NotBlank
@Size(min = 2, max = 20)
private String userName;

@NotBlank
@Email
private String email;
```

Benefits:

* Cleaner code
* Less boilerplate
* Reusable rules
* Easier maintenance
* Standardized validation

---

# Most Asked Interview Questions

## Why use validation?

To ensure incoming request data satisfies business rules before processing.

---

## Where should validation be applied?

Usually on DTOs.

---

## Which annotation activates validation?

```java
@Valid
```

---

## What happens if @Valid is missing?

Validation annotations are ignored.

---

## Which exception is thrown when validation fails?

```java
MethodArgumentNotValidException
```

---

## Difference Between @NotNull, @NotEmpty and @NotBlank

| Annotation | Rejects null | Rejects "" | Rejects "   " |
| ---------- | ------------ | ---------- | ------------- |
| @NotNull   | ✅            | ❌          | ❌             |
| @NotEmpty  | ✅            | ✅          | ❌             |
| @NotBlank  | ✅            | ✅          | ✅             |

---

# Fresher Interview Answer

> Validation in Spring Boot is used to ensure incoming request data satisfies predefined rules before reaching the business layer. We typically place validation annotations such as @NotBlank, @Size, and @Email on DTO fields and activate validation using @Valid in controller methods. If validation fails, Spring throws MethodArgumentNotValidException, which can be handled globally using @RestControllerAdvice to return a customized 400 Bad Request response.

---

# Quick Revision

* Validation checks incoming data.
* Validation is usually applied on DTOs.
* `@Valid` triggers validation.
* `MethodArgumentNotValidException` is thrown when validation fails.
* `@NotBlank` is most commonly used for Strings.
* Validation errors are often handled through Global Exception Handling.
* Validation reduces manual if-else checks and boilerplate code.
* Validation helps keep bad data away from business logic and databases.
