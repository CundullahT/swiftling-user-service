package com.swiftling.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("http://localhost:8762")
@RequestMapping("/demo")
public class DemoController {

    @GetMapping
    public String demo() {
        return "Hello World";
    }

}
