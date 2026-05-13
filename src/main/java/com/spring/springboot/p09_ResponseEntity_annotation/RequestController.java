package com.spring.springboot.p09_ResponseEntity_annotation;

import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RequestController {

    // Common logic ke liye ek sample list
   private List<String> dataStore = new ArrayList<>(Arrays.asList("Java", "Spring", "Hibernate"));

    // 1. Logic for fetching data (GET)
    @GetMapping("/item/{itemid}")
    public ResponseEntity<String> fetchData(@PathVariable(name = "itemid") int id) {
        if (id >= 0 && id < dataStore.size()) {
//            return ResponseEntity.ok(dataStore.get(id)); // Return specific item //This way is the shorthand way, just
//            for ok response as its default one for success
        return ResponseEntity
                .status(HttpStatus.OK)
                .header("headerKey", "ExampleHeaderValue")
                .body(dataStore.get(id)); //Not only body we can send data to 'Header' and 'Body' too,
            // through response obj. And that only is he real magic of ResponseEntity class, that we just have to set
            // everything in one obj not using multiple things to set data to response and hence avoiding the cumbersome
            // process
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Please Enter Valid itemID");

        //      we cannot do ResponseEntity.notfound("Please Enter Valid UserID") //Invalid // CompileTimeError

    }

    // 2. Logic for creating data (POST)
    @PostMapping("/item/{itemname}")
    public ResponseEntity<String> createData(@PathVariable(name = "itemname") String newItem) {
         dataStore.add(newItem); // List mein add karna
        return ResponseEntity.status(HttpStatus.CREATED).body("Item created successfully");
    }

    // 3. Logic for full update (PUT)
    @PutMapping("/item/{id}/{newData}")
    public ResponseEntity<String> updateFullData(@PathVariable int id, @PathVariable String newData) {
        if (id >= 0 && id < dataStore.size()) {
            dataStore.set(id, newData); // Purane ko naye se replace karna
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Updated successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Update failed: ID not found");
    }

    // 4. Logic for partial update (PATCH)
    @PatchMapping("/item/{id}/{partialInfo}")
    public ResponseEntity<String> updatePartialData(@PathVariable int id,@PathVariable String partialInfo) {
        if (id >= 0 && id < dataStore.size()) {
            String existing = dataStore.get(id);
            dataStore.set(id, existing + " " + partialInfo); // Sirf ek part modify karna
            return ResponseEntity.ok("Patched successfully");
        }
        return ResponseEntity.badRequest().body("Update failed: ID not found");
    }

    // 6. Logic for checking existence (HEAD)
    @RequestMapping(value = "/{id}", method = RequestMethod.HEAD) //Note: We use RequestMethod.HEAD here because HEAD is specifically designed to return only headers without a body. It's very fast and saves bandwidth.
    public ResponseEntity<Void> checkExistence(@PathVariable String id) {
        boolean exists = dataStore.contains(id); // Your logic here
        if (exists) {
            return ResponseEntity.noContent().build(); // 204: It exists, but no data to show
        }
        return ResponseEntity.notFound().build(); // 404: Not found
    /*Note: .build() vs .body()
     Dono saath mein nahi aayenge: ...body("").build() likhoge toh code phat jayega (Compile error).
     Shorthand Limit: ResponseEntity.notFound() ke baad .body() nahi lagta, wahan sirf .build() chalta hai.
     Agar 404 ke saath body chahiye, toh .status(404).body() wala manual rasta pakdo.

     1. .build()
        Ismein sirf Headers (jaise Status Code, Content-Type, custom headers) jaate hain. Response ki Body khali (empty) hoti hai.

        Example: 404 Not Found (Bas bata diya ki nahi mila, kuch bheja nahi).

     2. .body(data)
        Ismein Headers + Body dono jaate hain. Status code toh jayega hi, saath mein tumhara data (JSON ya String) bhi jayega.

        Example: 200 OK + {"name": "Laptop"}.

        In Short:
        .build() = "No Body, Only Status/Headers"
        .body() = "Status/Headers + Actual Data"
        Dono hi raste response ko finalize karte hain, bas farak "Samaan" (Data) ka hai. Ab jab tum Postman mein check karoge, toh dhyan dena ki .build() waale case mein "Body" tab ekdum blank dikhega!
     */

    }
    }

