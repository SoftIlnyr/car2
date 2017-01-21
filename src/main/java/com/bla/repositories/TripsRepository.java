package com.bla.repositories;

import com.bla.entities.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by softi on 20.01.2017.
 */
@Repository
public interface TripsRepository extends JpaRepository<Trip, Integer> {
    public List<Trip> findTop10ByOrderByDateDesc();
}
