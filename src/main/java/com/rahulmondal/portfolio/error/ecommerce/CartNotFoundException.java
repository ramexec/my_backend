package com.rahulmondal.portfolio.error.ecommerce;

public class CartNotFoundException extends RuntimeException{
    public CartNotFoundException(String message){
        super(message);
    }
}
