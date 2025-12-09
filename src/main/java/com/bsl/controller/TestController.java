package com.bsl.controller;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/Test")
public class TestController {

    @PutMapping
    public String test(String input) {
        System.out.println(input);
        return "Test successful";
    }
}
