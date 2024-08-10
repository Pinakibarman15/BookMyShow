package com.restoreBookshow.demo.repository;

import com.restoreBookshow.demo.models.Shows;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowRepository extends JpaRepository<Shows,Long> {
}
