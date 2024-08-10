package com.restoreBookshow.demo.repository;

import com.restoreBookshow.demo.models.ShowSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ShowSeatRepository extends JpaRepository<ShowSeat,Long> {
    List<ShowSeat> findAllByShow_HallId(Long id);
}
