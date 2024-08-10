package com.restoreBookshow.demo.services;

import com.restoreBookshow.demo.dtos.CreateShowRequest;
import com.restoreBookshow.demo.models.*;
import com.restoreBookshow.demo.repository.ShowRepository;
import com.restoreBookshow.demo.repository.ShowSeatRepository;
import com.restoreBookshow.demo.strategies.PricingStrategy;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class ShowService {
    private  final ShowSeatRepository showSeatRepository;
    private  HallService hallService;
    private ShowRepository showRepository;
    private Movieservice movieservice;
    private  SeatService seatService;
    private ShowSeatService showSeatService;


    public Shows createShow(CreateShowRequest request){
        Hall hall=hallService.getHallInternal(request.getHallId());
        if(hall==null){
            throw new NoSuchElementException("hall not exists");
        }
        Movie movie=movieservice.getMovieInternal(request.getMovieId());
        if(movie==null){
            throw new NoSuchElementException("movie not exists");
        }
        Shows show=Shows.builder().hall(hall)
                .startDate(request.getStartTime())
                .movie(movie)
                .duration(request.getDuration())
                .build();

Shows  saveshow=showRepository.save(show);
List<Seat> seats=seatService.getAll(request.getHallId());
List<ShowSeat> showSeats=seats.stream().map(seat->ShowSeat.builder()
        .seat(seat)
        .show(saveshow)
        .build()).toList();
//        showSeatRepository.saveAll(showSeats);
//        return showRepository.save(saveshow.toBuilder().showseats(showSeats).build());
        showSeatService.create(showSeats);
        return saveshow;
    }
 public Shows getShow(Long id){
        return showRepository.findById(id).orElseThrow(()-> new NoSuchElementException());
 }
}
