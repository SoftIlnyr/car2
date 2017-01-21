package com.bla.services.INTERFACES;

import com.bla.entities.Booking;

import java.util.List;

/**
 * Created by softi on 21.01.2017.
 */
public interface BookingService {
    Booking addBooking(Booking booking);

    void update(Booking booking);

    List<Booking> findAll();

    Booking findById(int id);

}
