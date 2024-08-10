package com.restoreBookshow.demo.repository;

import com.restoreBookshow.demo.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie,Long> {
    public Optional<Movie> findByName(String name);
  List<Movie> findAllByName(String name);
}
