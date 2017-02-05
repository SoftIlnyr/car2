package com.bla.services.INTERFACES;

import com.bla.entities.Passenger;

import java.util.List;


public interface PassengersService {
    Passenger addPassenger(Passenger passenger);

    Passenger findById(int id);

    void update(Passenger passenger);

    List<Passenger> findAll();
}
