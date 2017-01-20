package com.bla.services.IMPL;

import com.bla.entities.Passenger;
import com.bla.repositories.PassengersRepository;
import com.bla.services.INTERFACES.PassengersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by softi on 20.01.2017.
 */
@Service
@Transactional
public class PassengersServiceIMPL implements PassengersService {

    @Autowired
    PassengersRepository passengersRepository;

    @Override
    public Passenger addPassenger(Passenger passenger) {
        passengersRepository.save(passenger);
        return passenger;
    }

    @Override
    public Passenger findById(int id) {
        return passengersRepository.findOne(id);
    }

    @Override
    public void update(Passenger passenger) {
        passengersRepository.save(passenger);

    }

    @Override
    public List<Passenger> findAll() {
        return passengersRepository.findAll();
    }
}
