package com.restoreBookshow.demo.repository;

import com.restoreBookshow.demo.models.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<Seat,Long> {
    List<Seat>  findAllByHall_id(Long Id);
}
