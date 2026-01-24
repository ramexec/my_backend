package com.rahulmondal.portfolio.services;

import org.jspecify.annotations.Nullable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rahulmondal.portfolio.configs.AuthUtils;
import com.rahulmondal.portfolio.configs.CustomUserDetails;
import com.rahulmondal.portfolio.dto.requests.UserLoginRequestDTO;
import com.rahulmondal.portfolio.dto.requests.UserRegistrationRequestDTO;
import com.rahulmondal.portfolio.dto.response.UserLoginResponseDTO;
import com.rahulmondal.portfolio.models.Role;
import com.rahulmondal.portfolio.models.User;
import com.rahulmondal.portfolio.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;
    private final AuthUtils authUtils;
    private final UserRepository userRepository;

    public UserLoginResponseDTO login(UserLoginRequestDTO userLoginRequestDTO) {
       try{
       Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(userLoginRequestDTO.getUsername(), userLoginRequestDTO.getPassword())
       );

       User user = ((CustomUserDetails) authentication.getPrincipal()).getUser();
    
       String token = authUtils.generateAccessToken(user);
       return new UserLoginResponseDTO(token,user.getId(),user.getFirstName(),user.getSecondName());
       }
       catch(BadCredentialsException ex){
            throw ex;
       }

    }

    public UserLoginResponseDTO signup(UserRegistrationRequestDTO userRegistrationRequestDTO) {
        User user = userRepository.findByUsername(userRegistrationRequestDTO.getUsername()).orElse(null);
        if(user != null)
        {
            throw new IllegalStateException("User Alredy exists");
        }
        
        user = userRepository.save(User.builder()
                                    .username(userRegistrationRequestDTO.getUsername())
                                    .password(passwordEncoder.encode(userRegistrationRequestDTO.getPassword()))
                                    .firstName(userRegistrationRequestDTO.getFirstName())
                                    .secondName(userRegistrationRequestDTO.getSecondName())
                                    .email(userRegistrationRequestDTO.getEmail())
                                    .role(Role.ROLE_USER)
                                .build());

        return new UserLoginResponseDTO(null,user.getId()); 
    }


}
