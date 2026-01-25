package com.rahulmondal.portfolio.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.rahulmondal.portfolio.configs.CustomUserDetails;
import com.rahulmondal.portfolio.models.User;
import com.rahulmondal.portfolio.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService{
    private final UserRepository userRepository;

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElse(null);

        if(user == null) {
            throw new UsernameNotFoundException("Username: " + username + " Not Found");
        }
        return new CustomUserDetails(user);
    }

}
