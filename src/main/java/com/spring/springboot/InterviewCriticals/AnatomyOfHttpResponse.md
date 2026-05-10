# Anatomy of an HTTP Response (Deep Understanding)

After server processes request, it sends back an:

```text
HTTP Response
```

This response tells client:
- whether request succeeded or failed
- what data came back
- what type of data came back
- metadata about response

---

# Real HTTP Response Example

```http
HTTP/1.1 200 OK
Content-Type: application/json
Content-Length: 45
Cache-Control: no-cache
Set-Cookie: JSESSIONID=abc123

{
   "name":"Sachin",
   "age":21
}
```

---

# Complete Anatomy of HTTP Response

An HTTP response mainly contains:

```text
1. Status Line
2. Headers
3. Blank Line
4. Response Body
```

---

# Visual Structure

```text
STATUS LINE
HEADERS

BODY
```

Blank line separates:
- headers
- body

same as HTTP request.

---

# 1. Status Line

Example:

```http
HTTP/1.1 200 OK
```

Contains:

| Part | Meaning |
|---|---|
| HTTP/1.1 | HTTP version |
| 200 | Status code |
| OK | Status message |

---

# HTTP Version

Example:

```text
HTTP/1.1
```

Defines:
- protocol rules
- communication format

Usually developers don't manually care about this in Spring Boot.

---

# Status Code

MOST IMPORTANT PART.

Tells client:

```text
"What happened with request?"
```

---

# Common Status Codes

| Code | Meaning |
|---|---|
| 200 | Success |
| 201 | Resource created |
| 400 | Bad request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Resource not found |
| 500 | Internal server error |

---

# Example Meaning

## 200 OK

```text
Request successful
```

---

## 201 Created

Usually returned after:

```http
POST request
```

when new resource created.

---

## 401 Unauthorized

Meaning:

```text
Token/login missing or invalid
```

---

## 403 Forbidden

Meaning:

```text
User authenticated but not allowed
```

Example:

```text
User 10 trying to access admin resource
```

---

## 404 Not Found

Meaning:

```text
Requested URL/resource not found
```

Example:

```http
/users/9999
```

if user doesn't exist.

---

## 500 Internal Server Error

Meaning:

```text
Backend/server crashed internally
```

Example:
- NullPointerException
- database failure

---

# Final Flow of Status Line

```text
Controller finishes processing
        ↓
Spring creates HTTP status
        ↓
Client understands request result
```

---

# In Spring MVC

Example:

```java
@GetMapping("/users")
public ResponseEntity<User> get() {

    return ResponseEntity.ok(user);
}
```

Spring sends:

```http
HTTP/1.1 200 OK
```

automatically.

---

# 2. HTTP Response Headers

Headers contain:

```text
metadata about response
```

Examples:
- response type
- cookies
- caching rules
- security info

---

# Header Format

```http
Header-Name: value
```

---

# Important Response Headers

---

# Content-Type

Example:

```http
Content-Type: application/json
```

Meaning:

```text
Response body contains JSON
```

---

# Why Important

Browser/frontend must know:
- how to interpret response body

Examples:

| Content-Type | Meaning |
|---|---|
| application/json | JSON |
| text/html | HTML page |
| image/png | PNG image |
| application/pdf | PDF file |

---

# Spring MVC Connection

Spring selects:
- `MappingJackson2HttpMessageConverter`
- `StringHttpMessageConverter`
- etc.

then automatically sets proper:

```http
Content-Type
```

header.

---

# Final Flow

```text
Java Object
      ↓
HttpMessageConverter
      ↓
JSON Generated
      ↓
Content-Type set
```

---

# Content-Length

Example:

```http
Content-Length: 45
```

Meaning:

```text
Size of response body in bytes
```

Helps browser know:
- when response completed

---

# Set-Cookie

Example:

```http
Set-Cookie: JSESSIONID=abc123
```

Server asks browser:

```text
"Store this cookie."
```

Used for:
- sessions
- login tracking
- authentication

---

# Cache-Control

Example:

```http
Cache-Control: no-cache
```

Controls:
- browser caching
- proxy caching

---

# Location Header

Often used after:

```http
201 Created
```

Example:

```http
Location: /users/10
```

Meaning:

```text
New resource created here
```

---

# Authorization Related Headers

Sometimes backend sends:
- refreshed token
- auth info
- security headers

Example:

```http
WWW-Authenticate
```

---

# Final Flow of Headers

```text
Response Headers
      ↓
Provide metadata about:
- response type
- caching
- authentication
- cookies
- body size
```

---

# 3. Blank Line

Example:

```http
Content-Type: application/json

{
   "name":"Sachin"
}
```

Blank line separates:
- headers
- body

---

# Why Important

Server/client must know:

```text
"Headers ended here."
```

Otherwise parsing becomes impossible.

---

# 4. HTTP Response Body

Body contains:
actual response data.

---

# Example JSON Body

```json
{
   "name":"Sachin",
   "age":21
}
```

---

# Response Body Types

| Type | Example |
|---|---|
| JSON | REST APIs |
| HTML | Web pages |
| XML | Old enterprise systems |
| Binary | images/files |
| Plain Text | simple messages |

---

# Spring MVC Response Flow

Controller returns:

```java
return user;
```

Spring internally:

```text
Java Object
      ↓
@ResponseBody
      ↓
HttpMessageConverter
      ↓
JSON
      ↓
HTTP Response Body
```

---

# VERY IMPORTANT CONCEPT

## @RestController

```java
@RestController
```

internally means:

```java
@Controller + @ResponseBody
```

---

# What @ResponseBody Does

```text
"Write returned object directly into HTTP response body."
```

Without it:
Spring tries:

```text
View Resolution
```

like:
- JSP
- Thymeleaf
- HTML pages

---

# Example

## REST API

```java
@RestController
public class UserController {

    @GetMapping("/users")
    public User get() {
        return user;
    }
}
```

Returns:
```json
{
   "name":"Sachin"
}
```

---

## Traditional MVC

```java
@Controller
public class UserController {

    @GetMapping("/home")
    public String home() {
        return "home";
    }
}
```

Spring interprets:

```text
"home"
```

as:
```text
home.jsp / home.html
```

NOT response body.

---

# Final Reverse Flow

```text
Controller returns Java Object
        ↓
@ResponseBody activated
        ↓
HttpMessageConverter selected
        ↓
Java Object converted to JSON
        ↓
DispatcherServlet receives response
        ↓
Tomcat sends HTTP response
        ↓
Client receives response
```

---

# Complete End-to-End Response Anatomy

```text
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
HTTP Response Body Generated
    ↓
Response Headers Added
    ↓
HTTP Status Added
    ↓
Tomcat Sends Response
    ↓
Browser/Postman Receives Response
```

---

# Real Full Response Example

```http
HTTP/1.1 200 OK
Content-Type: application/json
Content-Length: 45
Cache-Control: no-cache

{
   "name":"Sachin",
   "age":21
}
```

---

# Final Mental Model

```text
Status Line
→ tells result of request

Headers
→ provide metadata about response

Blank Line
→ separates headers and body

Response Body
→ actual returned data

@ResponseBody
→ tells Spring to write object into response body

HttpMessageConverter
→ converts Java object into JSON/XML/text

Tomcat
→ sends final HTTP response back to client
```