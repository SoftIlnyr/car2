package com.bla.services.INTERFACES;

import com.bla.entities.Passenger;

import java.util.List;

/**
 * Created by softi on 20.01.2017.
 */
public interface PassengersService {
    Passenger addPassenger(Passenger passenger);

    Passenger findById(int id);

    void update(Passenger passenger);

    List<Passenger> findAll();
}
