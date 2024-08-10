package com.restoreBookshow.demo.models;

import com.restoreBookshow.demo.enums.BookingStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Booking  extends BaseModel {
    @ManyToOne
    private Customer customer;
    @ManyToOne
    private Shows show;

    private Double amount;
    private Date bookdAt;

    private BookingStatus status;
    @ManyToMany
    private List<ShowSeat> seats=new ArrayList<>();

}
