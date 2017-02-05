package com.bla.repositories;

import com.bla.entities.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TripsRepository extends JpaRepository<Trip, Integer> {
    public List<Trip> findTop10ByOrderByDateDesc();

    public List<Trip> findAllByOrderByDateDesc();

    public List<Trip> findByStatusOrderByDateDesc(final String status);

    public List<Trip> findByDepartureAndDestinationOrderByDateDesc(String destination, String departure);
}
