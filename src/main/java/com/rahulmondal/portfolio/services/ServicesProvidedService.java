package com.rahulmondal.portfolio.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.rahulmondal.portfolio.dto.DTOmapper;
import com.rahulmondal.portfolio.dto.requests.CreateServiceRequest;
import com.rahulmondal.portfolio.dto.response.ServicesProvidedDTO;
import com.rahulmondal.portfolio.models.ServicesProvided;
import com.rahulmondal.portfolio.models.User;
import com.rahulmondal.portfolio.repository.ServicesProvidedRepository;
import com.rahulmondal.portfolio.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ServicesProvidedService {
 
    private final ServicesProvidedRepository serviceProvidedRepository;
    private final DTOmapper dtoMapper;
    private final UserRepository userRepository;

    public List<ServicesProvidedDTO> getServicesByUser(Long userId) {
        return serviceProvidedRepository.findByUserId(userId)
                .stream()
                .map(dtoMapper::toServiceDto)
                .collect(Collectors.toList());
    }

    public Boolean createService(CreateServiceRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName(); 
        
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        ServicesProvided service = new ServicesProvided();
        service.setTitle(request.getTitle());
        service.setDescription(request.getDescription());
        service.setPrice(request.getPrice());
        service.setFeatured(request.isFeatured());
        service.setUser(user);

        serviceProvidedRepository.save(service);
        
        return true;
    }
}
