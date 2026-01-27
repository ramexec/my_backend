package com.rahulmondal.portfolio.error;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import io.jsonwebtoken.ExpiredJwtException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ApiError> handleUsernameNotFoundException(UsernameNotFoundException exception){
        ApiError  apiError = new ApiError("Username Not Found ! " + exception.getMessage(), HttpStatus.NOT_FOUND) ;
        return new ResponseEntity<>(apiError,apiError.getStatusCode());
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<ApiError> jwtExpiredException(ExpiredJwtException exception){
        ApiError  apiError = new ApiError("Jwt token Expired: " + exception.getMessage(), HttpStatus.FORBIDDEN) ;
        return new ResponseEntity<>(apiError,apiError.getStatusCode());
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiError> badCredsException(BadCredentialsException exception){
        ApiError  apiError = new ApiError("Bad Credentials: " + exception.getMessage(), HttpStatus.BAD_REQUEST) ;
        return new ResponseEntity<>(apiError,apiError.getStatusCode());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleGenericExcption(Exception exception){
        ApiError  apiError = new ApiError("Internal Server Error: " + exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR) ;
        return new ResponseEntity<>(apiError,apiError.getStatusCode());
    }

    
}
