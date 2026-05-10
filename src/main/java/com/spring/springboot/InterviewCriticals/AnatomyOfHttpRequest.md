# Anatomy of an HTTP Request (Deep Understanding)

Before understanding Spring Boot request flow, we must first understand:

```text
"What actually comes inside an HTTP request?"
```

Because Spring MVC, Tomcat, DispatcherServlet, HttpMessageConverters —
ALL work on top of this HTTP request structure.

---

# Real HTTP Request Example

```http
POST /users?id=10 HTTP/1.1
Host: localhost:8080
Content-Type: application/json
Accept: application/json
Authorization: Bearer xyz123
User-Agent: PostmanRuntime

{
   "name":"Sachin",
   "age":21
}
```

---

# Complete Anatomy of HTTP Request

An HTTP request mainly contains:

```text
1. Request Line
2. Headers
3. Blank Line
4. Request Body (optional)
```

---

# Visual Structure

```text
REQUEST LINE
HEADERS

BODY
```

The blank line is VERY important.

It tells server:

```text
"Headers ended. Body starts now."
```

---

# 1. Request Line

Example:

```http
POST /users?id=10 HTTP/1.1
```

This first line contains:

| Part | Meaning |
|---|---|
| POST | HTTP Method |
| /users?id=10 | Resource Path |
| HTTP/1.1 | HTTP Version |

---

# HTTP Method (Verb)

Tells server:

```text
"What action should be performed?"
```

---

## Common HTTP Methods

| Method | Purpose |
|---|---|
| GET | Fetch data |
| POST | Send/Create data |
| PUT | Update full resource |
| PATCH | Partial update |
| DELETE | Remove resource |

---

# Example

```http
GET /users
```

Meaning:

```text
"Give me users."
```

---

```http
POST /users
```

Meaning:

```text
"Create/save new user."
```

---

# Resource Path

Example:

```http
/users?id=10
```

Contains:
- endpoint path
- query parameters

---

# Query Parameters

Example:

```http
/users?id=10&sort=asc
```

Query params start after:

```text
?
```

Used for:
- filtering
- pagination
- searching

---

# In Spring MVC

```java
@GetMapping("/users")
public String getUser(@RequestParam int id) {
}
```

Spring extracts:

```text
id=10
```

automatically.

---

# HTTP Version

Example:

```http
HTTP/1.1
```

Defines:
- request format
- protocol rules
- connection behavior

Modern systems may use:
- HTTP/1.1
- HTTP/2
- HTTP/3

But Spring MVC developer usually does not manually handle this.

---

# Final Flow of Request Line

```text
Request Line
   ↓
Defines:
- WHAT action?
- WHICH resource?
- WHICH protocol version?
```

---

# 2. HTTP Headers

Headers are:

```text
metadata about request
```

Meaning:
extra information about:
- client
- body
- authentication
- accepted response type

---

# Header Format

```http
Header-Name: value
```

Example:

```http
Content-Type: application/json
```

---

# Important Request Headers

---

# Host Header

```http
Host: localhost:8080
```

Tells:
```text
Which server/domain client wants to access.
```

Very important in HTTP/1.1.

---

# Content-Type Header

```http
Content-Type: application/json
```

Tells server:

```text
"What type of data is inside request body?"
```

Examples:

| Content-Type | Meaning |
|---|---|
| application/json | JSON data |
| text/plain | Plain text |
| multipart/form-data | File upload |
| application/xml | XML |

---

# Why Content-Type Is SUPER Important

Spring MVC checks:

```http
Content-Type
```

to select correct:

```text
HttpMessageConverter
```

Example:

| Content-Type | Converter |
|---|---|
| application/json | MappingJackson2HttpMessageConverter |
| text/plain | StringHttpMessageConverter |

---

# Final Flow

```text
Content-Type
      ↓
Spring MVC checks it
      ↓
Correct HttpMessageConverter selected
```

---

# Accept Header

Example:

```http
Accept: application/json
```

Meaning:

```text
"I want response in JSON format."
```

This is called:

```text
Content Negotiation
```

Spring checks:
- what client accepts
- what server can produce

then chooses response format.

---

# Final Flow

```text
Accept Header
      ↓
Spring checks expected response format
      ↓
Selects response converter
```

---

# Authorization Header

Example:

```http
Authorization: Bearer xyz123
```

Used for:
- JWT tokens
- authentication
- security

Spring Security checks this header.

---

# User-Agent Header

Example:

```http
User-Agent: Chrome
```

Tells server:
- browser name
- operating system
- client info

Useful for:
- analytics
- browser compatibility
- debugging

---

# Custom Headers

Developers can create custom headers.

Example:

```http
X-App-Version: v2
```

Used for:
- tracking
- microservices
- API versioning

---

# Final Flow of Headers

```text
Headers
   ↓
Provide metadata about:
- body type
- authentication
- response format
- client details
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

The empty line separates:
- headers
- body

---

# Why Blank Line Exists

Server must know:

```text
"Headers ended here."
```

Otherwise server cannot identify:
- where headers stop
- where body starts

---

# 4. HTTP Request Body

Body contains:
actual data sent by client.

Usually used in:
- POST
- PUT
- PATCH

---

# Example JSON Body

```json
{
   "name":"Sachin",
   "age":21
}
```

---

# Why Body Exists

To send:
- form data
- JSON
- XML
- files
- binary data

---

# In Spring MVC

Example:

```java
@PostMapping("/users")
public User save(@RequestBody User user) {
}
```

---

# What @RequestBody Means

```text
"Take HTTP body and convert it into Java object."
```

---

# Internal Flow

```text
HTTP Body
    ↓
HttpMessageConverter
    ↓
Java Object
    ↓
Controller Method
```

---

# How JSON Becomes Java Object

Incoming JSON:

```json
{
   "name":"Sachin"
}
```

Spring selects:

```text
MappingJackson2HttpMessageConverter
```

Uses:

```text
Jackson ObjectMapper
```

Internally converts:

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

Huge productivity improvement.

---

# Important Spring MVC Annotations Related to HTTP Request

---

# @RequestParam

Reads query parameters.

Example:

```java
@GetMapping("/users")
public String get(@RequestParam int id) {
}
```

URL:

```http
/users?id=10
```

Spring extracts:
```text
id=10
```

---

# Final Flow

```text
Query Parameter
      ↓
@RequestParam
      ↓
Method Parameter
```

---

# @PathVariable

Reads path values.

Example:

```java
@GetMapping("/users/{id}")
public String get(@PathVariable int id) {
}
```

URL:

```http
/users/10
```

Spring extracts:
```text
id=10
```

---

# Final Flow

```text
URL Path
    ↓
@PathVariable
    ↓
Method Parameter
```

---

# @RequestHeader

Reads headers.

Example:

```java
@GetMapping
public String get(
    @RequestHeader("Authorization") String token
) {
}
```

Spring extracts header value automatically.

---

# Final Flow

```text
HTTP Header
      ↓
@RequestHeader
      ↓
Method Parameter
```

---

# Complete Request Anatomy Flow

```text
HTTP Request
    ↓
Request Line
    ↓
Headers
    ↓
Blank Line
    ↓
Body
    ↓
Tomcat
    ↓
DispatcherServlet
    ↓
Spring MVC
    ↓
@RequestMapping Matching
    ↓
HttpMessageConverter
    ↓
Controller Method
```

---

# Final Mental Model

```text
Request Line
→ tells WHAT action to perform

Headers
→ give metadata about request

Blank Line
→ separates headers and body

Body
→ contains actual data

Spring MVC
→ reads all this information

Annotations like:
@RequestBody
@RequestParam
@PathVariable
@RequestHeader

help Spring extract required data
and inject it into controller methods automatically.
```

HTTP requests mainly contain:
- request line
- headers
- body. :contentReference[oaicite:0]{index=0}

Headers provide metadata like content type, accepted response type, and authorization. :contentReference[oaicite:1]{index=1}

The request body usually carries data for POST/PUT/PATCH requests. :contentReference[oaicite:2]{index=2}