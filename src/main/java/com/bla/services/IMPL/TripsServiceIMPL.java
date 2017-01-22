package com.bla.services.IMPL;

import com.bla.entities.Trip;
import com.bla.repositories.TripsRepository;
import com.bla.services.INTERFACES.TripsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by softi on 20.01.2017.
 */
@Service
@Transactional
public class TripsServiceIMPL implements TripsService {

    @Autowired
    TripsRepository tripsRepository;

    @Override
    public Trip addTrip(Trip trip) {
        return tripsRepository.save(trip);
    }

    @Override
    public void update(Trip trip) {
        tripsRepository.save(trip);
    }

    @Override
    public List<Trip> findAll() {
        return tripsRepository.findAll();
    }

    @Override
    public List<Trip> findAllOrderDate() {
        return tripsRepository.findAllByOrderByDateDesc();
    }

    @Override
    public List<Trip> findByStatusOrderDate() {
        return tripsRepository.findByStatusOrderByDateDesc("Ожидание");
    }

    @Override
    public List<Trip> findBySearch(String departure, String destination) {
        return tripsRepository.findByDepartureAndDestinationOrderByDateDesc(departure, destination);
    }


    @Override
    public Trip findById(int id) {
        return tripsRepository.findOne(id);
    }

    public List<Trip> lastTrips() {
        return tripsRepository.findTop10ByOrderByDateDesc();
    }
}
