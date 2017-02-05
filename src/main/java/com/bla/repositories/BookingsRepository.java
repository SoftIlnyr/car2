package com.bla.repositories;

import com.bla.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BookingsRepository extends JpaRepository<Booking, Integer> {
}
