package com.bla.repositories;

import com.bla.entities.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface DriversRepository extends JpaRepository<Driver, Integer> {

    List<Driver> findTop9ByOrderByRatingDesc();

}
