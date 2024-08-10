package com.restoreBookshow.demo.models;

import com.restoreBookshow.demo.enums.SeatStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder(toBuilder = true)
public class ShowSeat extends BaseModel{
    @ManyToOne
    @JoinColumn(name="show_id")
    private Shows show;
    @ManyToOne
    private Seat seat;

    @Enumerated
    private SeatStatus status;
}
