package com.springtests.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApplicationRepository {

    @GetMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }
}
