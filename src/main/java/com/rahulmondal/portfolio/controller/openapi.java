package com.rahulmondal.portfolio.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/openapi")
public class openapi {

    @GetMapping("/greet")
    public String greet(){
        return "Hellow World ";
    }
}
