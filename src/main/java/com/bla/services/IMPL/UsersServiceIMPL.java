package com.bla.services.IMPL;

import com.bla.services.INTERFACES.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.bla.entities.User;
import com.bla.repositories.UsersRepository;

import java.util.List;


@Service
@Transactional
public class UsersServiceIMPL implements UsersService {

    @Autowired
    public UsersRepository usersRepository;

    public User addUser(User user) {
        usersRepository.save(user);
        return user;
    }

    public User findById(int id) {
        User user = usersRepository.findOne(id);
        return user;
    }

    public void update(User user) {
        usersRepository.save(user);
    }

    public List<User> findAll() {
        return (List<User>) usersRepository.findAll();
    }

    @Override
    public User findByNickname(String name) {
        return usersRepository.findByNicknameIgnoreCase(name);
    }

    @Override
    public User findByEmail(String email) {
        return usersRepository.findByEmailIgnoreCase(email);
    }

    public UsersRepository getUsersRepository() {
        return usersRepository;
    }

    public void setUsersRepository(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }
}
