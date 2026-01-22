package com.rahulmondal.portfolio.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rahulmondal.portfolio.dto.response.openapi.UserUpdatesResponseDTO;
import com.rahulmondal.portfolio.services.OpenApiService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/openapi")
public class OpenApiController {

    private final OpenApiService openApiService;
    
    @GetMapping("/greet")
    public String greet(){
        return "Hellow World ";
    }

    @GetMapping("/updates")
    public ResponseEntity<List<UserUpdatesResponseDTO>> getUserUpdates() {
        return ResponseEntity.ok(openApiService.getUserUpdates());
    }
}
