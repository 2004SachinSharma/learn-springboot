# Spring Boot Internal Execution Flow

This document explains the internal mechanics of a Spring Boot application from the moment the "Run" button is clicked.

---

## 1. JVM & Main Method Entry

- **Action:** The IDE executes `java -jar` or directly runs the main class.

Example:

```java
public static void main(String[] args) {
    SpringApplication.run(MyApplication.class, args);
}
```

- **Why:** Every Java application starts from the `main()` method. At this point, only the JVM exists — Spring has not started yet.

- **Practical:** Put a breakpoint inside `main()` and run in Debug mode to see execution start.

---

## 2. SpringApplication.run() Initialization

- **Action:** The `run()` method creates and initializes the `SpringApplication` object.

Internal class:

```text
org.springframework.boot.SpringApplication
```

- **Why:** Spring Boot now starts preparing the application environment.

It:
- reads `application.properties` / `application.yml`
- loads OS environment variables
- loads JVM system properties
- checks dependencies present in classpath

Example:

```properties
server.port=8081
```

- **Practical:** You can debug inside:

```text
SpringApplication -> run()
```

from Spring Boot source code.

---

## 3. Creating the Application Context (The IoC Container)

- **Action:** Spring creates the IoC Container (`ApplicationContext`).

Usually for web apps:

```text
AnnotationConfigServletWebServerApplicationContext
```

- **Why:** This becomes the "Brain" of Spring.

The container is responsible for:
- creating beans
- storing beans
- dependency injection
- managing lifecycle

Without this container:
- `@Autowired`
- `@Service`
- `@Repository`

would not work.

- **Practical:** Print context class:

```java
System.out.println(context.getClass());
```

---

## 4. The Scanning & Definition Phase (The Blueprint Map)

### Component Scanning

- **Action:** `@ComponentScan` scans packages for:

```java
@Component
@Service
@Repository
@Controller
@Configuration
```

Example:

```java
@Service
public class UserService {
}
```

Spring detects this class and plans to manage it as a Bean.

---

### Auto Configuration

- **Action:** `@EnableAutoConfiguration` activates Spring Boot auto-configurations.

Spring reads:

```text
META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports
```

inside:

```text
spring-boot-autoconfigure.jar
```

Example entries:

```text
DataSourceAutoConfiguration
WebMvcAutoConfiguration
```

---

### Result

- Spring creates a **BeanDefinition Map**.

At this stage:
- mostly real objects are NOT created yet
- only metadata/blueprints are registered

Think of it like:

```text
"Spring is preparing the construction plan before building objects."
```

---

## 5. Conditional Filtering & Dependency Resolution

- **Action:** Spring evaluates conditions like:

```java
@ConditionalOnClass
@ConditionalOnMissingBean
```

Example:

```java
@ConditionalOnClass(DataSource.class)
```

means:

```text
"Only configure this if DataSource class exists."
```

---

### Example Logic

If developer already created custom bean:

```java
@Bean
public DataSource myDataSource() {
}
```

then Boot skips default datasource auto-configuration.

---

### Dependency Resolution

Spring determines:
- which bean depends on which bean
- correct creation order

Example:

```text
UserService → needs UserRepository
```

So Spring creates:
1. UserRepository
2. then UserService

---

## 6. Bean Instantiation & Dependency Injection (DI)

- **Action:** During `context.refresh()`, Spring creates actual bean objects.

Example:

```java
@Service
public class UserService {

    @Autowired
    private UserRepository repo;
}
```

Spring internally:
- creates `UserRepository`
- creates `UserService`
- injects repository into service

---

### Internal Classes Involved

```text
DefaultListableBeanFactory
AutowiredAnnotationBeanPostProcessor
```

---

### Why This Matters

Instead of:

```java
repo = new UserRepository();
```

Spring automatically provides dependencies.

Benefits:
- loose coupling
- easier testing
- maintainable architecture

---

## 7. Embedded Web Server Startup

- **Action:** After context refresh completes, Spring Boot starts embedded server:

```text
Tomcat / Jetty / Undertow
```

Main classes:

```text
TomcatServletWebServerFactory
DispatcherServlet
```

---

### Why This Is Powerful

Traditional Spring:

```text
Application deployed INSIDE external Tomcat
```

Spring Boot:

```text
Tomcat runs INSIDE application
```

Now app can run directly using:

```bash
java -jar app.jar
```

---

### Practical

Console log:

```text
Tomcat started on port(s): 8080
```

---

## 8. Runners Execution

- **Action:** Any class implementing:

```java
CommandLineRunner
ApplicationRunner
```

gets executed after startup.

Example:

```java
@Component
public class MyRunner implements CommandLineRunner {

    @Override
    public void run(String... args) {
        System.out.println("Application Started");
    }
}
```

---

## Final Result

Application startup completes.

Spring prints:

```text
Started MyApplication in X seconds
```

Now the application is fully ready to:
- accept HTTP requests
- execute business logic
- connect to databases
- serve APIs

---

# Final Mental Flow

```text
JVM Starts
    ↓
main()
    ↓
SpringApplication.run()
    ↓
Environment Prepared
    ↓
ApplicationContext Created
    ↓
Component Scanning
    ↓
AutoConfigurations Loaded
    ↓
BeanDefinitions Registered
    ↓
Beans Created
    ↓
Dependencies Injected
    ↓
Tomcat Started
    ↓
Application Ready
```