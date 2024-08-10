package com.restoreBookshow.demo.services;

import com.restoreBookshow.demo.dtos.CreateHallRequest;
import com.restoreBookshow.demo.dtos.SeatPosition;
import com.restoreBookshow.demo.enums.SeatType;
import com.restoreBookshow.demo.models.Hall;
import com.restoreBookshow.demo.models.Seat;
import com.restoreBookshow.demo.repository.HallRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Data
public class HallService {
    private SeatService seatservice;
    private HallRepository hallRepository;

    public static List<Seat> toSeat(Hall hall, Map<SeatType, List<SeatPosition>> seatPositions) {
        return seatPositions.entrySet().stream().flatMap(entry -> {
            SeatType seatType = entry.getKey();
            List<SeatPosition> positions = entry.getValue();

            return positions.stream().map(seatPosition -> Seat.builder().seatType(seatType).rowno(seatPosition.getRowno()).columnNo(seatPosition.getColumnno()).hall(hall).build());
        }).toList();
    }
       public Hall createHall(CreateHallRequest request){
        Hall hallRequest=Hall.builder().name(request.getName()).features(request.getFeatures()).build();
        Hall initialHall= hallRepository.save(hallRequest);

        List<Seat> seats=toSeat(initialHall,request.getSeatRanges());
        List<Seat> savedSeats=seatservice.saveAll(seats);
        return hallRepository.save(initialHall.toBuilder().seats(savedSeats).build());
       }

       public Hall getHallInternal(Long id){
        return hallRepository.findById(id).orElse(null);

       }
}
