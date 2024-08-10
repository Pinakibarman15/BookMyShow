package com.restoreBookshow.demo.services;

import com.restoreBookshow.demo.dtos.CreateBookingRequest;
import com.restoreBookshow.demo.enums.BookingStatus;
import com.restoreBookshow.demo.enums.SeatStatus;
import com.restoreBookshow.demo.exceptions.AlreadyBookedException;
import com.restoreBookshow.demo.models.Booking;
import com.restoreBookshow.demo.models.Customer;
import com.restoreBookshow.demo.models.ShowSeat;
import com.restoreBookshow.demo.models.Shows;
import com.restoreBookshow.demo.repository.BookingRepository;
import com.restoreBookshow.demo.repository.ShowSeatRepository;
import com.restoreBookshow.demo.strategies.PricingStrategy;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.security.InvalidParameterException;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class BookingService {
    private BookingRepository bookingRepository;
    private CustomerService customerService;
    private ShowSeatService showSeatService;

    private ShowService showService;
    //Qualifier("")
    //@condiftionalOnproperty(
    // values="price.stratety"
    // )
    private PricingStrategy pricingStrategy;


    public Booking CreateBooking(CreateBookingRequest request){
        //validate customer id
        Customer customer=customerService.getCustomerInternal(request.getCustomerId());
        if(customer==null){
            throw new NoSuchElementException("customer does not exits");
        }

        //validate show
        Shows show = showService.getShow(request.getShowId());
        if (show == null) {
            throw new NoSuchElementException("no such show exists");
        }


        List<ShowSeat> lockedSeats=lockseats(request);

        //save the booking
        Booking booking=Booking.builder()
                .customer(customer)
                .show(show)
                .seats(lockedSeats)
                .bookdAt(new Date())
                .status(BookingStatus.PENDING)
                .build();
        Double amount=pricingStrategy.calculatePrice(booking,lockedSeats);
        Booking withAmount=booking.toBuilder().amount(amount).build();
        return bookingRepository.save(withAmount);
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)  // to handle booking server running on multiple instance(distributed system).So,  need DB level lock
    private  List<ShowSeat> lockseats(CreateBookingRequest request) {
        //validate showseats user asked for
        List<ShowSeat> showSeatList = showSeatService.getShowSeats(request.getShowSeatsId());
        //convert to streams
        //filter check size
        //check if seat id is avilable or not
        for (ShowSeat seat : showSeatList) {
            //validate showeseats available or not
            if (seat.getStatus() != SeatStatus.AVAILABLE) {
                throw new AlreadyBookedException(seat.getSeat().getId());
            }
        }

        //        for(ShowSeat seat: showSeatList){
//            seat.setStatus(SeatStatus.LOCKED);
//        }
        //if available marked the seats locked for that user
        List<ShowSeat> lockedSeats = showSeatList.stream().map(seat -> seat.toBuilder().status(SeatStatus.LOCKED).build()).toList();
        //save the seats in the DB
        return showSeatService.saveAll(lockedSeats);
    }


}
