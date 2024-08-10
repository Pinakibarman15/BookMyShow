package com.restoreBookshow.demo.strategies;

import com.restoreBookshow.demo.models.Booking;
import com.restoreBookshow.demo.models.ShowSeat;

import java.util.List;

public interface PricingStrategy {
    Double calculatePrice(Booking booking, List<ShowSeat> showseats);

}
