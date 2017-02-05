package com.bla.services.IMPL;

import com.bla.entities.Automobile;
import com.bla.repositories.AutosRepository;
import com.bla.services.INTERFACES.AutosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class AutosServiceIMPL implements AutosService {

    @Autowired
    AutosRepository autosRepository;

    @Override
    public Automobile addAuto(Automobile automobile) {
        autosRepository.save(automobile);
        return automobile;
    }

    @Override
    public void update(Automobile automobile) {
        autosRepository.save(automobile);
    }

    @Override
    public List<Automobile> findAll() {
        return autosRepository.findAll();
    }

    @Override
    public Automobile findById(int id) {
        return autosRepository.findOne(id);
    }
}
