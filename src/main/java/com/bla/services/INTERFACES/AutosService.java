package com.bla.services.INTERFACES;

import com.bla.entities.Automobile;

import java.util.List;

public interface AutosService {
    Automobile addAuto(Automobile automobile);

    void update(Automobile automobile);

    List<Automobile> findAll();

    Automobile findById(int id);
}
