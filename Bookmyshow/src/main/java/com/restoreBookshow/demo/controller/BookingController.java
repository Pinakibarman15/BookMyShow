package com.restoreBookshow.demo.controller;

import com.restoreBookshow.demo.dtos.CreateBookingRequest;
import com.restoreBookshow.demo.models.Booking;
import com.restoreBookshow.demo.services.BookingService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class BookingController {
    private BookingService bookingService;
    //store the booking
    @PostMapping("/booking")
    public Booking  createBooking(@RequestBody CreateBookingRequest request){
        return bookingService.CreateBooking(request);

    }
}
