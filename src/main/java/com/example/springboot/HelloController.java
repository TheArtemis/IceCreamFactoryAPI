package com.example.springboot;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/icecream")
@Tag(name="Ice Cream (Old)")
@Deprecated
public class HelloController {
    @GetMapping("/{icecreamId}")
    public String getIcecream(@PathVariable Long icecreamId) {
        if (icecreamId == 1) {
            return "Chocolate";
        } else if (icecreamId == 2) {
            return "Cream";
        } else {
            return "Unknown";
        }
    }

    @GetMapping("/test1")
    public String test1() {
        return "You are in the test area that has HOT SWAPP";
    }

    @PostMapping("/sayHi")
    public String sayHi(@RequestParam String name, @RequestParam int age) {
        String s = "Hi " + name + " " + age;
        return s;
    }
}

/*
*   @GetMapping exposes a GET endpoint
*   @PostMapping exposes a POST endpoint
*
*   @RequestParam is used to force a parameter onto the POST request
*
* */
