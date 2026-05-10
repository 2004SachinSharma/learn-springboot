# How Web Server Works in Spring Boot (Complete Internal Flow)

This document explains the complete request-response lifecycle in a Spring Boot application using:
- Tomcat
- Spring MVC
- DispatcherServlet
- Controllers
- HttpMessageConverters
- JSON Serialization & Deserialization

It also explains:
- how Spring finds the correct controller method
- how `@RequestMapping`, `@GetMapping`, `@PostMapping` work
- how request body gets converted into Java objects
- how response comes back as JSON
- complete reverse flow

---

# 1. Client Sends HTTP Request

Example request:

```http
POST /users HTTP/1.1
Host: localhost:8080
Content-Type: application/json
Accept: application/json

{
   "name":"Sachin",
   "age":21
}
```

---

## What Happens

The client can be:
- browser
- frontend app
- mobile app
- Postman

It sends:
- URL
- HTTP method
- headers
- body

to the server.

---

## Important Headers

### Content-Type

```http
Content-Type: application/json
```

Meaning:

```text
"I am sending JSON data."
```

---

### Accept

```http
Accept: application/json
```

Meaning:

```text
"I want response in JSON format."
```

This process is called:

```text
Content Negotiation
```

---

# Final Flow

```text
Client sends HTTP request
        ↓
Tomcat receives request
```

---

# 2. Embedded Tomcat Receives Request

## What Happens

Spring Boot internally starts embedded Tomcat.

Tomcat listens on port:

```properties
server.port=8080
```

When request arrives:

```text
Client
   ↓
Tomcat
```

Tomcat creates:
- `HttpServletRequest`
- `HttpServletResponse`

objects.

---

## Why Tomcat Exists

Tomcat is:
- web server
- servlet container

Responsibilities:
- receive HTTP requests
- manage threads
- create request/response objects
- send responses back

Spring Boot itself is NOT the web server.

It uses:
- Tomcat
- Jetty
- Undertow

---

# Final Flow

```text
Client
   ↓
Tomcat
   ↓
DispatcherServlet
```

---

# 3. Tomcat Forwards Request to DispatcherServlet

## What Happens

Tomcat forwards every request to:

```text
DispatcherServlet
```

Main class:

```text
org.springframework.web.servlet.DispatcherServlet
```

---

## What is DispatcherServlet?

DispatcherServlet is the:

```text
Front Controller of Spring MVC
```

Meaning:
ALL requests first come here.

It controls:
- request routing
- controller invocation
- response generation

---

# What is Spring MVC?

Spring MVC is Spring's web framework.

MVC means:

```text
Model + View + Controller
```

Important Spring MVC components:
- DispatcherServlet
- HandlerMapping
- HandlerAdapter
- Controller
- HttpMessageConverter
- ViewResolver

---

# Final Flow

```text
Tomcat
   ↓
DispatcherServlet
   ↓
Find correct controller
```

---

# 4. DispatcherServlet Finds Correct Controller Method

## What Happens

DispatcherServlet now asks:

```text
"Which controller method should handle this request?"
```

Spring uses:

```text
HandlerMapping
```

Main implementation:

```text
RequestMappingHandlerMapping
```

---

# How Spring Finds Correct Method

Spring checks annotations like:

```java
@GetMapping("/users")
@PostMapping("/users")
@RequestMapping("/users")
```

Example:

```java
@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping
    public String getUsers() {
        return "All Users";
    }

    @PostMapping
    public String saveUser() {
        return "Saved";
    }
}
```

---

# How Matching Happens

| Incoming Request | Matching Method |
|---|---|
| GET /users | getUsers() |
| POST /users | saveUser() |

---

## Meaning of Mapping Annotations

### @RequestMapping

Base mapping annotation.

Example:

```java
@RequestMapping("/users")
```

means:

```text
"All APIs inside this controller start with /users"
```

---

### @GetMapping

Shortcut for:

```java
@RequestMapping(method = RequestMethod.GET)
```

Meaning:

```text
"Handle GET request"
```

---

### @PostMapping

Meaning:

```text
"Handle POST request"
```

Used when client sends data to server.

---

# Final Flow

```text
DispatcherServlet
   ↓
HandlerMapping
   ↓
Correct Controller Method Found
```

---

# 5. DispatcherServlet Uses HandlerAdapter

## What Happens

DispatcherServlet now calls:

```text
HandlerAdapter
```

Usually:

```text
RequestMappingHandlerAdapter
```

This adapter actually invokes controller method.

---

## Why HandlerAdapter Exists

It acts like a bridge between:
- DispatcherServlet
- controller methods

It standardizes method invocation.

---

# Final Flow

```text
DispatcherServlet
   ↓
HandlerAdapter
   ↓
Controller Method Executes
```

---

# 6. Request Body Reading Starts

Example request body:

```json
{
   "name":"Sachin"
}
```

Controller:

```java
@PostMapping
public User save(@RequestBody User user) {
    return user;
}
```

---

# What @RequestBody Means

```java
@RequestBody
```

tells Spring:

```text
"Take HTTP request body and convert it into Java object."
```

---

# 7. HttpMessageConverter Selection

## What Happens

Spring MVC checks:

```http
Content-Type
```

header.

Example:

```http
application/json
```

Spring selects suitable:

```text
HttpMessageConverter
```

---

# Purpose of HttpMessageConverter

Convert:

```text
HTTP Body ↔ Java Object
```

---

# Most Important HttpMessageConverters

## 1. MappingJackson2HttpMessageConverter

Used for:

```text
application/json
```

Uses:

```text
Jackson ObjectMapper internally
```

Converts:
- JSON → Java Object
- Java Object → JSON

MOST important converter in REST APIs.

---

## 2. StringHttpMessageConverter

Used for:

```text
text/plain
```

Example:

```java
return "Hello";
```

---

## 3. ByteArrayHttpMessageConverter

Used for:
- images
- PDFs
- binary data

Example:

```java
@GetMapping("/image")
public byte[] getImage() {
}
```

---

## 4. FormHttpMessageConverter

Used for:

```text
application/x-www-form-urlencoded
```

HTML form data.

---

# Example Conversion

Incoming JSON:

```json
{
   "name":"Sachin"
}
```

gets converted into:

```java
User user = new User();
user.setName("Sachin");
```

automatically.

---

# Before Spring MVC Automation

Earlier developers manually handled:

```java
BufferedReader
InputStream
ObjectMapper
```

Example:

```java
BufferedReader br = request.getReader();
```

Spring MVC automated this using:
- `@RequestBody`
- HttpMessageConverters

---

# Final Flow

```text
Request JSON
   ↓
HttpMessageConverter
   ↓
Java Object
   ↓
Controller Method
```

---

# 8. Controller Executes Business Logic

Example:

```java
@PostMapping
public User save(@RequestBody User user) {

    service.save(user);

    return user;
}
```

---

# Internal Layer Flow

```text
Controller
    ↓
Service
    ↓
Repository
    ↓
Database
```

---

# Responsibility of Each Layer

| Layer | Responsibility |
|---|---|
| Controller | Handle request/response |
| Service | Business logic |
| Repository | Database operations |

---

# Final Flow

```text
Controller
   ↓
Service
   ↓
Repository
   ↓
Database
```

---

# 9. Reverse Response Flow Starts

Now response travels back.

Controller returns:

```java
return user;
```

DispatcherServlet receives Java object.

---

# 10. @RestController and @ResponseBody

## Very Important Concept

```java
@RestController
```

internally means:

```java
@Controller + @ResponseBody
```

---

# What @ResponseBody Means

```text
"Do NOT return JSP/HTML page.
Write returned object directly into HTTP response body."
```

Without `@ResponseBody`,
Spring MVC tries View Resolution:

```text
Controller → JSP/HTML page
```

With `@ResponseBody`,
Spring returns:
- JSON
- XML
- text

using HttpMessageConverters.

---

# Final Flow

```text
Controller returns Java Object
        ↓
@ResponseBody activated
        ↓
Convert object into HTTP response
```

---

# 11. Spring Checks Accept Header

Example:

```http
Accept: application/json
```

Spring asks:

```text
"In what format should response be sent?"
```

Possible formats:
- JSON
- XML
- text
- binary

---

# 12. HttpMessageConverter Converts Response

Spring again selects suitable converter.

Example:

```text
MappingJackson2HttpMessageConverter
```

Conversion:

```text
Java Object → JSON
```

Output:

```json
{
   "name":"Sachin"
}
```

---

# Final Flow

```text
Java Object
    ↓
HttpMessageConverter
    ↓
JSON Response
```

---

# 13. DispatcherServlet Sends Response to Tomcat

DispatcherServlet now sends:
- response body
- status code
- headers

back to Tomcat.

---

# 14. Tomcat Sends Final HTTP Response

Final response:

```http
HTTP/1.1 200 OK
Content-Type: application/json

{
   "name":"Sachin"
}
```

gets sent back to:
- browser
- frontend
- Postman

---

# Complete End-to-End Flow

```text
CLIENT REQUEST FLOW

Client
   ↓
Tomcat
   ↓
DispatcherServlet
   ↓
HandlerMapping
   ↓
HandlerAdapter
   ↓
HttpMessageConverter
   ↓
Controller
   ↓
Service
   ↓
Repository
   ↓
Database

=================================

RESPONSE FLOW

Database
   ↓
Repository
   ↓
Service
   ↓
Controller
   ↓
@ResponseBody
   ↓
HttpMessageConverter
   ↓
DispatcherServlet
   ↓
Tomcat
   ↓
Client
```

---

# Final Mental Model

```text
Tomcat handles HTTP communication.

DispatcherServlet controls request routing.

Spring MVC manages the web architecture.

@RequestMapping annotations help Spring find correct controller methods.

@RequestBody converts request body into Java objects.

@ResponseBody converts Java objects into HTTP responses.

HttpMessageConverters perform actual conversion.

Controllers handle requests.

Services contain business logic.

Repositories interact with database.
```