package com.restoreBookshow.demo.services;

import com.restoreBookshow.demo.models.ShowSeat;
import com.restoreBookshow.demo.repository.ShowSeatRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ShowSeatService {
    private ShowSeatRepository showSeatRepository;
    public List<ShowSeat> saveAll(List<ShowSeat> showSeatList){
        return showSeatRepository.saveAll(showSeatList);
    }

    public List<ShowSeat> getShowSeats(List<Long> showSeatIds){
        return showSeatRepository.findAllById(showSeatIds);
    }

    public void create(List<ShowSeat> showSeats) {
        showSeatRepository.saveAll(showSeats);
    }
}
