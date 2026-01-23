package com.rahulmondal.portfolio.services;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.time.LocalDateTime;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.rahulmondal.portfolio.configs.CustomUserDetails;
import com.rahulmondal.portfolio.dto.requests.UserUpdatesRequestDTO;
import com.rahulmondal.portfolio.models.User;
import com.rahulmondal.portfolio.models.UserUpdates;
import com.rahulmondal.portfolio.repository.UserUpdatesRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserUpdatesService {

    private final UserUpdatesRepository userUpdatesRepository;

    public boolean postUpdate(UserUpdatesRequestDTO req){
        UserUpdates userUpdates = new UserUpdates();
        
        try{
            User user = ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
            userUpdates.setCreatedAt(LocalDateTime.now());
            userUpdates.setUser(user);
            userUpdates.setText(req.getText());
            userUpdates.setSubject(req.getSubject());
            userUpdatesRepository.save(userUpdates);
            return true;
        }catch(Exception ex){
            log.error("Error Occured at postUpdat :", ex);      
            return false;
        }
    }
}
