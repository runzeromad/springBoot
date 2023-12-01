package com.example.firstproject.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // rest controller
public class FirstApiController {
    @GetMapping("api/hello")
    public String hello(){
        return "hello world!";
    }
}
