package com.bla.services.INTERFACES;

import com.bla.entities.Driver;

import java.util.List;


public interface DriversService {
    Driver addDriver(Driver driver);

    Driver findById(int id);

    void update(Driver driver);

    List<Driver> findAll();

    List<Driver> getBest();

}
