package com.restoreBookshow.demo.exceptions;

public class MovieAlreadyExistsException extends RuntimeException{
    public MovieAlreadyExistsException(String name){
        super("movew exsts");
    }
}
