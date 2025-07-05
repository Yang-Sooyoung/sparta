package com.spring.sample;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {
    @GetMapping("/sample")
    public String sample() {
        return "Hello World";
    }
}
