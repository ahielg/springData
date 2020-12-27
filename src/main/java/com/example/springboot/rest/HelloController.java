package com.example.springboot.rest;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HelloController {

    @RequestMapping("/hey")
    public String index() {
        return "Greetings from Spring Boot!";
    }

}