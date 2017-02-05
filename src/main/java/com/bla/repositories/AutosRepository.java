package com.bla.repositories;

import com.bla.entities.Automobile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AutosRepository extends JpaRepository<Automobile, Integer> {

}
