package com.bla.services.INTERFACES;

import com.bla.entities.User;

import java.util.List;


public interface UsersService {
    User addUser(User user);

    User findById(int id);

    void update(User user);

    List<User> findAll();

    User findByNickname(String name);

    User findByEmail(String email);
}
