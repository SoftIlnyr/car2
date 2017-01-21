package com.bla.services.IMPL;

import com.bla.entities.Booking;
import com.bla.repositories.BookingsRepository;
import com.bla.services.INTERFACES.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by softi on 21.01.2017.
 */
@Service
@Transactional
public class BookingsServiceIMPL implements BookingService {

    @Autowired
    BookingsRepository bookingsRepository;

    @Override
    public Booking addBooking(Booking booking) {
        return bookingsRepository.save(booking);
    }

    @Override
    public void update(Booking booking) {
        bookingsRepository.save(booking);
    }

    @Override
    public List<Booking> findAll() {
        return bookingsRepository.findAll();
    }

    @Override
    public Booking findById(int id) {
        return bookingsRepository.findOne(id);
    }
}
