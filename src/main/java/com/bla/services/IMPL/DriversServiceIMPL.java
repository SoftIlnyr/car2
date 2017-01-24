package com.bla.services.IMPL;

import com.bla.entities.Driver;
import com.bla.repositories.DriversRepository;
import com.bla.services.INTERFACES.DriversService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by softi on 19.01.2017.
 */
@Service
@Transactional
public class DriversServiceIMPL implements DriversService {

    @Autowired
    DriversRepository driversRepository;

    @Override
    public Driver addDriver(Driver driver) {
        driversRepository.save(driver);
        return driver;
    }

    @Override
    public Driver findById(int id) {
        Driver driver = driversRepository.findOne(id);
        return driver;
    }

    @Override
    public void update(Driver driver) {
        driversRepository.save(driver);
    }

    @Override
    public List<Driver> findAll() {
        return driversRepository.findAll();
    }

    @Override
    public List<Driver> getBest() {
        return driversRepository.findTop9ByOrderByRatingDesc();
    }

}
