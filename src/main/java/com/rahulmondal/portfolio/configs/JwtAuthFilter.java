package com.rahulmondal.portfolio.configs;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import com.rahulmondal.portfolio.services.CustomUserDetailsService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter{

    private final CustomUserDetailsService userDetailsService;
    private final AuthUtils authUtils;
    private final HandlerExceptionResolver handlerExceptionResolver;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
              
            try{
            final String reqHeader = request.getHeader("Authorization");
            if(reqHeader == null || !reqHeader.startsWith("Bearer")){
                filterChain.doFilter(request, response);
                return;
            } 

            String token = reqHeader.split("Bearer ")[1];
            String username = authUtils.extractUsername(token);
            
            if(username != null && SecurityContextHolder.getContext().getAuthentication() == null ){
                CustomUserDetails user = userDetailsService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken token2 = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(token2);
            }
            filterChain.doFilter(request, response);
            }
            catch(Exception exception){
                handlerExceptionResolver.resolveException(request, response, null, exception);
            }
           
    }

}