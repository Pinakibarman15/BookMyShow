package com.restoreBookshow.demo.services;

import com.restoreBookshow.demo.exceptions.MovieAlreadyExistsException;
import com.restoreBookshow.demo.exceptions.MovieNoFoundException;
import com.restoreBookshow.demo.models.Movie;
import com.restoreBookshow.demo.repository.MovieRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class Movieservice {

    @Autowired
    private MovieRepository mvrpo;
    public Movie createMovie(Movie mv){
        //movie validation if same name movie exists
        Optional<Movie> existingMovie= mvrpo.findByName(mv.getName());
        if(existingMovie.isPresent()){
          throw new MovieAlreadyExistsException(mv.getName()); //clent error bad request 400
        }
        return mvrpo.save(mv);
    }
    public Movie getMovie(Long movieId){
        return mvrpo.findById(movieId).orElseThrow(() -> new MovieNoFoundException("movie"));
    }

    public Movie getMovieInternal(Long movieId){
        return mvrpo.findById(movieId).orElse(null);
    }
}
