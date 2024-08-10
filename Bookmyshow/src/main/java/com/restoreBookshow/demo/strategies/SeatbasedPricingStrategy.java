package com.restoreBookshow.demo.strategies;

import com.restoreBookshow.demo.enums.SeatType;
import com.restoreBookshow.demo.models.Booking;
import com.restoreBookshow.demo.models.ShowSeat;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("seatbasedStrategy")
@Primary
public class SeatbasedPricingStrategy implements PricingStrategy {

// create another strategy
    @Override
    public Double calculatePrice(Booking booking, List<ShowSeat> showseats) {
        //iterate over show seat
        // theter id ,seat type for each showseat get the price
        //add all price (list of doubles )
        return showseats.stream().mapToDouble(seat-> getPrice(seat.getSeat().getSeatType())).sum();
    }

    //move to database
    //seat type price mapping

    //here ocp violation
    // to avoid data Move to database of  theater id,seattype --> price  mapping
    private static double getPrice(SeatType type){
        switch(type){
            case VIP ->{
                return 2000;
            }
            case GOLD ->{

                return 500;
            }
        }
        //to detect some invalid seat type as argument
        throw new IllegalArgumentException("invalid");
    }
}
