package com.rahulmondal.portfolio.error;

public class UserAlreadyExistsException extends RuntimeException{
    public UserAlreadyExistsException(String message ){
        super(message);
    }
}
