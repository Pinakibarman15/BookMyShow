package com.restoreBookshow.demo.controller;

import com.restoreBookshow.demo.dtos.CreateMovieRequest;
import com.restoreBookshow.demo.models.Movie;
import com.restoreBookshow.demo.services.Movieservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movie")
public class MovieController {
    @Autowired
    private Movieservice mvs;

    //create movie
    @PostMapping("/id")
    public Movie createMovie(@RequestBody CreateMovieRequest request){
       return  mvs.createMovie(request.toMovie());


    }

    @GetMapping("/{movieId}/{showId}")
    public ResponseEntity<Movie> getMovie(@PathVariable("movieid") Long movieId,@PathVariable("id") Long showid){
        Movie mv=mvs.getMovie(movieId);
        return ResponseEntity.status(HttpStatus.OK).body(mv);

    }
}
