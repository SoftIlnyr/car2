package com.bla.repositories;

import com.bla.entities.Automobile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by softi on 19.01.2017.
 */
@Repository
public interface AutosRepository extends JpaRepository<Automobile, Integer> {

}
