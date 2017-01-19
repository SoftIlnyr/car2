package com.bla.services.INTERFACES;

import com.bla.entities.Automobile;

import java.util.List;

/**
 * Created by softi on 19.01.2017.
 */
public interface AutosService {
    Automobile addAuto(Automobile automobile);

    void update(Automobile automobile);

    List<Automobile> findAll();
}
