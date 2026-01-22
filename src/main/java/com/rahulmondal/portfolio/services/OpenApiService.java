package com.rahulmondal.portfolio.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.rahulmondal.portfolio.dto.response.openapi.UserUpdatesResponseDTO;

@Service
public class OpenApiService {

    public List<UserUpdatesResponseDTO> getUserUpdates(){

        List<UserUpdatesResponseDTO> res = new ArrayList<>();
        
        res.add( UserUpdatesResponseDTO.builder()
        .time(LocalDateTime.now())
        .subject("test from server")
        .text("some updates happenin ")
        .build());
        
        return res;
    }
}
