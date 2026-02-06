package com.rahulmondal.portfolio.error.ecommerce;


public class OrderNotFoundException extends RuntimeException{
    public OrderNotFoundException(String msg){
        super(msg);
    }
}
