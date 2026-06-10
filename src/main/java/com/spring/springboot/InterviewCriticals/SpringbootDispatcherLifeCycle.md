 Spring Boot Request Lifecycle (DispatcherServlet Flow)

## 1. Request enters the server (Tomcat)

Your request first reaches Embedded Tomcat (or Jetty/Undertow). It is not Spring yet. Tomcat converts HTTP request into HttpServletRequest and forwards it to Spring.

---

## 2. DispatcherServlet (Front Controller)

DispatcherServlet is the entry point of Spring MVC. Every request goes through it. It follows Front Controller Design Pattern.

---

## 3. Handler Mapping

DispatcherServlet finds which controller should handle the request.

Example:
GET /users/{id} → UserController.getUser()

If not found → 404.

---

## 4. HandlerAdapter

DispatcherServlet uses HandlerAdapter to execute the controller method.

---

## 5. Argument Resolution

Spring resolves method parameters:

- @PathVariable → from URL
- @RequestParam → from query string
- @RequestHeader → from headers

If conversion fails → MethodArgumentTypeMismatchException  
If missing param → MissingServletRequestParameterException  
If missing header → MissingRequestHeaderException

---

## 6. @RequestBody Conversion

Spring uses HttpMessageConverters (like Jackson) to convert JSON → Java object.

If invalid JSON:
→ HttpMessageNotReadableException

---

## 7. Validation (@Valid)

Bean Validation runs here.

If invalid:
→ MethodArgumentNotValidException

---

## 8. Controller Execution

Business logic executes in controller/service layer.

---

## 9. Response Conversion

Java object → JSON using HttpMessageConverters.

---

## 10. Exception Handling Flow

If any error occurs during the Request or Response lifecycle, the application handles it level-by-level. Exceptions do not teleport; they travel backwards up the call stack (Repository → Service → Controller). If no layer catches the exception, it finally reaches the `DispatcherServlet`, which acts as the ultimate safety net.

### The Core Flow:

`DispatcherServlet` → `ExceptionResolvers` (Interface Chain) → `@ControllerAdvice` (Your Class) → `Response`

---

### Step-by-Step Breakdown:

#### 1. DispatcherServlet (The Orchestrator)

* **Role:** Yeh khud exception handle nahi karta. Jaise hi iske paas controller se udta hua exception aata hai, yeh bina dimaag lagaye is poori responsibility ko **`HandlerExceptionResolver`** (Interface) ki chain ko delegate (pass) kar deta hai.

#### 2. ExceptionResolvers (The Interface Chain)

Interface khud kuch nahi dhoondta, balki Spring Boot iske **3 major implementation classes** ki ek chain run karta hai (Line-by-line check hota hai):

* **A. `ExceptionHandlerExceptionResolver` (Priority 1):** * **Job:** Yeh sabse important class hai. Yeh sabse pehle us Controller mein local `@ExceptionHandler` dhoondti hai jahan error aaya. Agar wahan nahi milta, toh yeh seedhe aapke global **`@ControllerAdvice`** ke andar jaakar sahi `@ExceptionHandler` ko dhoondti hai.
* **B. `ResponseStatusExceptionResolver` (Priority 2):** * **Job:** Agar pehle wale resolver ko kuch nahi milta, toh yeh check karta hai ki kya thrown exception class ke upar **`@ResponseStatus`** annotation laga hai? Agar haan, toh yeh wahan se HTTP status code set kar deta hai.
* **C. `DefaultHandlerExceptionResolver` (Priority 3 - Last Safety Net):** * **Job:** Jab dono resolvers haar maan jaate hain, tab yeh Spring ke standard internal exceptions (jaise bad JSON input ke liye `HttpMessageNotReadableException`) ko unke sahi HTTP status codes (jaise 400 Bad Request) mein convert karta hai.

#### 3. @ControllerAdvice (Your Custom Class)

* **Role:** Yeh aapka likha hua global interceptor class hai. Jab `ExceptionHandlerExceptionResolver` ise select karta hai, toh iske andar maujood matching **`@ExceptionHandler`** method chalata hai.

#### 4. Response (The Output)

* **Role:** `@ControllerAdvice` ka method jo bhi data return karta hai, use `HttpMessageConverters` JSON/HTML mein serialize karte hain, aur `DispatcherServlet` use ek clean **`ResponseEntity` / Response** ke roop mein client ko bhej deta hai.

---

# Full Lifecycle Flow

Client Request
↓
Tomcat
↓
DispatcherServlet
↓
HandlerMapping
↓
HandlerAdapter
↓
Argument Resolution
↓
Message Conversion
↓
Validation
↓
Controller Execution
↓
Response Conversion
↓
Client Response

---

# Where Exceptions Occur

| Stage | Exception |
|------|----------|
| URL not found | 404 |
| Type mismatch | MethodArgumentTypeMismatchException |
| Missing param | MissingServletRequestParameterException |
| Missing header | MissingRequestHeaderException |
| Bad JSON | HttpMessageNotReadableException |
| Validation failure | MethodArgumentNotValidException |

---

# One Line Summary

Spring Boot is a pipeline:
Request → Map → Bind → Validate → Execute → Convert Response
"""
