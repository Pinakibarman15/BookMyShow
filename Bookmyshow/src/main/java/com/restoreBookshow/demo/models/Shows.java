package com.restoreBookshow.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Builder(toBuilder = true)
@AllArgsConstructor
public class Shows extends BaseModel {

    @ManyToOne
    @JoinColumn(name="movie_id")
    private Movie movie;
    private Date startDate;
    private Integer duration;

    @ManyToOne
    private Hall hall;

    @OneToMany(mappedBy="show")
    private List<ShowSeat> showseats= new ArrayList<>();

}
