package com.restoreBookshow.demo.models;

import com.restoreBookshow.demo.enums.MovieFeature;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;
@Entity
@Builder(toBuilder = true)
public class Hall extends BaseModel{
    private String name;

    @OneToMany
    private List<Seat> seats=new ArrayList<>();

    @ElementCollection
    @Enumerated
    private List<MovieFeature> features=new ArrayList<>();
}
