package com.bla.services.INTERFACES;

import com.bla.entities.Booking;

import java.util.List;


public interface BookingService {
    Booking addBooking(Booking booking);

    void update(Booking booking);

    List<Booking> findAll();

    Booking findById(int id);

}
