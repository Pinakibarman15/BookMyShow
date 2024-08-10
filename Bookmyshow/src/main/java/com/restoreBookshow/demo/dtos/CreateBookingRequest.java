package com.restoreBookshow.demo.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class CreateBookingRequest {
    //customr|show|showseat|amount|bookat

    private Long customerId;
    private Long showId;
    //seat ids
    private List<Long> showSeatsId =new ArrayList<>();
}
