package com.bla.services.INTERFACES;

import com.bla.entities.Trip;

import java.util.List;

/**
 * Created by softi on 20.01.2017.
 */
public interface TripsService {

    Trip addTrip(Trip trip);

    void update(Trip trip);

    List<Trip> findAll();

    List<Trip> findAllOrderDate();

    List<Trip> findByStatusOrderDate();

    List<Trip> findBySearch(String departure, String destination);

    Trip findById(int id);

    List<Trip> lastTrips();
}
