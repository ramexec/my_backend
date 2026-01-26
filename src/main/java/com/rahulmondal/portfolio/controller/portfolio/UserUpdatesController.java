package com.rahulmondal.portfolio.controller.portfolio;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rahulmondal.portfolio.dto.requests.UserUpdatesRequestDTO;
import com.rahulmondal.portfolio.services.UserUpdatesService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RequiredArgsConstructor
@RestController
@RequestMapping("/updates")
public class UserUpdatesController {

    private final UserUpdatesService userUpdatesService;

    @PostMapping("/push")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Boolean> pushUpdate(@RequestBody UserUpdatesRequestDTO req) {
        return ResponseEntity.ok(userUpdatesService.postUpdate(req));
    }
    
}
