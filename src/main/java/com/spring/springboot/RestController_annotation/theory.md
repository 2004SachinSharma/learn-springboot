# Guide to @RestController in Spring Boot

The `@RestController` annotation is the gateway that allows your application to interact with the outside world. It is the core building block for **RESTful Web Services**.

---

## 1. The "Secret Sauce" (The Combo)
In modern Spring development, `@RestController` is a convenience annotation that combines two major functionalities:

* **`@Controller`**: Marks the class as a Spring Bean and tells the `DispatcherServlet` that this class handles web requests[cite: 1].
* **`@ResponseBody`**: Tells Spring that every method in this class will return data, not a physical HTML page[cite: 1]. This data is automatically serialized (usually into **JSON**) and sent directly to the client[cite: 1].

> **Key Benefit:** Before Spring Boot, you had to manually add `@ResponseBody` to every single method[cite: 1]. `@RestController` handles this globally for the entire class[cite: 1].

---

## 2. The Request Flow (Under the Hood)
When a client hits a URL (e.g., `localhost:8080/api/v1/greet`)[cite: 1]:

1. **DispatcherServlet** (the traffic cop) receives the HTTP request[cite: 1].
2. It identifies the class marked with `@RestController` that matches the path[cite: 1].
3. It executes the method with the corresponding **Mapping** (e.g., `@GetMapping`)[cite: 1].
4. Your logic is executed, and a Java Object/String is returned[cite: 1].
5. The **Jackson** library kicks in to convert the Java object into JSON format[cite: 1].



---

## 3. Core Mapping Annotations
| Annotation | HTTP Method | Purpose |
| :--- | :--- | :--- |
| `@GetMapping` | GET | Fetch or Read data[cite: 1] |
| `@PostMapping` | POST | Create or Save new data[cite: 1] |
| `@PutMapping` | PUT | Update existing data[cite: 1] |
| `@DeleteMapping` | DELETE | Remove data[cite: 1] |

---

## 4. Code Implementation Example
```java
@RestController
@RequestMapping("/api/v1") // Base URL for all methods in this class
public class StudentController {

    @GetMapping("/greet")
    public String sayHello() {
        return "Hello Sachin, your API is working!"; // Returns raw String
    }

    @GetMapping("/student")
    public Student getStudent() {
        // Returns an Object; Jackson automatically converts this to JSON
        return new Student(1, "Sachin Sharma"); 
    }
}
```

## 5. Comparison: @Controller vs @RestController
@Controller: Traditional. Returns a View (HTML/JSP). Requires a View Resolver. Best for Server-Side Rendering[cite: 1].

@RestController: Modern. Returns Data (JSON/XML). No View Resolver needed. Best for React/Angular frontends or mobile apps[cite: 1].

Java Full Stack Mastery • Spring Boot Technical Documentation
