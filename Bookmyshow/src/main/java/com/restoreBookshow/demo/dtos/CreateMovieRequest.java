package com.restoreBookshow.demo.dtos;

import com.restoreBookshow.demo.enums.Languange;
import com.restoreBookshow.demo.enums.MovieFeature;
import com.restoreBookshow.demo.models.Movie;
import lombok.AllArgsConstructor;
import lombok.Data;


import java.util.ArrayList;
import java.util.List;



@Data
public class CreateMovieRequest {
    private String name;
    private Double rating;
    private List<Languange> languages=new ArrayList<>();
    private  List<MovieFeature> features=new ArrayList<>();

    //transform
    public Movie toMovie(){
        return Movie.builder().name(name).rating(rating).lang(languages).features(features)
                .build();
    }
}
