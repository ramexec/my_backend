package com.rahulmondal.portfolio.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rahulmondal.portfolio.dto.requests.CreateServiceRequest;
import com.rahulmondal.portfolio.services.ServicesProvidedService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/services")
@RestController
@RequiredArgsConstructor
public class ServicesProvidedController {

    private final ServicesProvidedService servicesProvided;

    @PostMapping("/create")
    // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Boolean> createService(@RequestBody CreateServiceRequest request) {
        return ResponseEntity.ok(servicesProvided.createService(request));
    }
}
