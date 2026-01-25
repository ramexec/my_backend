package com.rahulmondal.portfolio.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.rahulmondal.portfolio.dto.DTOmapper;
import com.rahulmondal.portfolio.dto.response.ServicesProvidedDTO;
import com.rahulmondal.portfolio.dto.response.openapi.UserUpdatesResponseDTO;
import com.rahulmondal.portfolio.models.User;
import com.rahulmondal.portfolio.repository.ServicesProvidedRepository;
import com.rahulmondal.portfolio.repository.UserRepository;
import com.rahulmondal.portfolio.repository.UserUpdatesRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OpenApiService {

    @Value("${owner.id}")
    private Long ownerId;

    private final UserUpdatesRepository userUpdatesRepository;
    private final UserRepository userRepository;
    private final ServicesProvidedRepository servicesProvidedRepository;
    private final DTOmapper dtoMapper;
    
    public List<UserUpdatesResponseDTO> getTopUserUpdates(){

        List<UserUpdatesResponseDTO> res = new ArrayList<>();
        User user = userRepository.findById(ownerId).orElseThrow();

        
        userUpdatesRepository.findTop50ByUserOrderByCreatedAtDesc(user).stream().forEach(
        u -> res.add(UserUpdatesResponseDTO.builder()
        .time(u.getCreatedAt())
        .subject(u.getSubject())
        .text(u.getText())
        .build()));
        
        return res;
    }

    public List<ServicesProvidedDTO> getDefaultServices() {
        return servicesProvidedRepository.findByUserId(ownerId)
                .stream()
                .map(dtoMapper::toServiceDto)
                .collect(Collectors.toList());
    }
}
