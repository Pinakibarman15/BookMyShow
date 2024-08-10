package com.restoreBookshow.demo.exceptions;

public class CustomerNotFoundException extends RuntimeException{
    public CustomerNotFoundException(Long id){
        super("customer:" +id);
    }
}
