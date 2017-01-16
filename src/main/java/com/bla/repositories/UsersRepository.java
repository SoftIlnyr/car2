package com.bla.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bla.entities.User;

/**
 * Created by softi on 24.04.2016.
 */
@Repository
public interface UsersRepository extends JpaRepository<User, Integer> {
    User findByNickname(String nickname);

    User findByEmail(String email);

    User findByNicknameIgnoreCase(String name);

    User findByEmailIgnoreCase(String email);
}
