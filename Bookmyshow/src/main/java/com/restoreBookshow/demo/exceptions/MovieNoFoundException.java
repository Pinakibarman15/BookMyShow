package com.restoreBookshow.demo.exceptions;

public class MovieNoFoundException extends  RuntimeException {
    public MovieNoFoundException(String movie) {
        super("not exists");
    }
}
