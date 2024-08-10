package com.restoreBookshow.demo.services;

import com.restoreBookshow.demo.models.Seat;
import com.restoreBookshow.demo.repository.SeatRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SeatService {
    private SeatRepository seatRepository;
    public List<Seat> saveAll(List<Seat> seats){
        return seatRepository.saveAll(seats);
    }

    public List<Seat> getAll(Long hallId){
        return seatRepository.findAllByHall_id(hallId);
    }
}
