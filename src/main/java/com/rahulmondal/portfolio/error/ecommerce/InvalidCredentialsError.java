package com.rahulmondal.portfolio.error.ecommerce;

public class InvalidCredentialsError extends RuntimeException{
    public InvalidCredentialsError(String msg){
        super(msg);
    }
}
