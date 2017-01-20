package com.bla.repositories;

import com.bla.entities.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by softi on 20.01.2017.
 */
@Repository
public interface TripsRepository extends JpaRepository<Trip, Integer> {
}
