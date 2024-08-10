package com.restoreBookshow.demo.exceptions;

import java.util.List;

public class AlreadyBookedException extends RuntimeException{
    public AlreadyBookedException(Long showseats){
        super("seats already booked:"+showseats);
    }
}
