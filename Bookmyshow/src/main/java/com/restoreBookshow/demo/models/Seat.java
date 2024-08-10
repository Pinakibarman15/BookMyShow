package com.restoreBookshow.demo.models;


import com.restoreBookshow.demo.enums.SeatType;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@AllArgsConstructor
public class Seat extends BaseModel {
    private Integer rowno;
    private Integer columnNo;

    @Enumerated
    private SeatType seatType;

    @ManyToOne
    @JoinColumn(name="hall_id")
    private Hall hall;
}
