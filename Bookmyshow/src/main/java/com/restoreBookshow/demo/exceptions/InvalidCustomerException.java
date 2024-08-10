package com.restoreBookshow.demo.exceptions;

public class InvalidCustomerException extends RuntimeException{

    public InvalidCustomerException(){
        super("email mandatory");
    }
}
