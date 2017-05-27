package com.boot.user;


import com.boot.user.model.User;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public interface UserService {

    Collection<User> getAll();

    Optional<User> getById(Integer id);

    Optional<User> getByLogin(String login);

    User modify(User user) throws IllegalArgumentException;

    User add(User user) throws IllegalArgumentException;

    void delete(Integer id) throws IllegalArgumentException;

    void hashAll();
}
