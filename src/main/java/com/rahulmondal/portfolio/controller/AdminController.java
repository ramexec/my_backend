package com.rahulmondal.portfolio.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/echo")
    public ResponseEntity<?> echo() {
        return ResponseEntity.ok("Access Granted");
    }
    
}
