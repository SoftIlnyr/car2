package com.bla.repositories;

import com.bla.entities.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PassengersRepository extends JpaRepository<Passenger, Integer> {
}
