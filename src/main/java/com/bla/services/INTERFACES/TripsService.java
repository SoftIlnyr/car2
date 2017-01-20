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

    Trip findById(int id);
}
