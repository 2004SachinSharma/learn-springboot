package com.spring.springboot.p10_API_Versioning;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class V4_MediaTypeOrContentNegotiationVersioning {

//4. Media Type Versioning (Advanced)
//Also called:
//Content Negotiation Versioning
//Version sent in:
//Accept Header
//Example:
//Accept: application/vnd.company.app-v1+json
    @GetMapping(
            value = "/users",
            produces = "application/vnd.company.app-v1+json"
    )
    public ResponseEntity<String> getUsersV1() {
        return ResponseEntity.ok("Response from API V1");
    }

    @GetMapping(
            value = "/users",
            produces = "application/vnd.company.app-v2+json"
    )
    public ResponseEntity<String> getUsersV2() {
        return ResponseEntity.ok("Response from API V2");

    }
}
//Why This Is Less Common
//Because:

/*
Complex
Harder testing
Harder frontend integration
Not beginner friendly
*/

//Mostly large enterprise APIs use this.
//Which One Should YOU Learn First?
//Priority order:
/*
Priority	Learn
1	URI Versioning
2	Request Parameter Versioning
3	Header Versioning
4	Media Type Versioning
Which One Is Best in Real Industry?

Depends.

Most practical:
URI Versioning
/api/v1/users

Because:

Easy maintenance
Easy documentation
Easy debugging
Easy frontend usage
Important Interview Question
"Can we avoid API versioning?"

YES sometimes.

If change is:

backward compatible
optional field addition only

then no need for new version.

        Example:

Old:

        {
        "name":"Sachin"
        }

New:

        {
        "name":"Sachin",
        "college":"RJIT"
        }

Old frontend still works.

So versioning not always needed.

This is a very mature backend engineering concept.

Important Industry Reality

Bad developers create:

v1
        v2
v3
        v4
v5

too frequently.

Good backend engineers try to:

design stable APIs
minimize breaking changes
keep versions long-lived

Because maintaining multiple versions is expensive.

Best Practice

Good practice:

        /api/v1/users

Bad practice:

        /api/version1/users

Because shorter + cleaner is standard.
*/

