package com.restoreBookshow.demo.exceptions;

public class EmailAllreadyExistsException extends RuntimeException{
    public EmailAllreadyExistsException(String emil){
        super("customer:" +emil);
    }
}
