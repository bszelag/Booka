package com.boot.user.impl;


import com.boot.user.UserService;
import com.boot.user.model.User;
import com.boot.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    public UserRepository userRepository;

    @Override
    public Collection<User> getAll() {
        return StreamSupport.stream(userRepository.findAll().spliterator(), false).
                collect(Collectors.toList());
    }

    @Override
    public User getById(String id) {
        return userRepository.findOne(id);
    }

    @Override
    public User modify(User user) {
        if (user.getLogin() == null || !userRepository.exists(user.getLogin()))
            return null;
        return userRepository.save(user);
    }

    @Override
    public boolean delete(String id) {
        if(userRepository.exists(id)){
            userRepository.delete(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean add(User user) {
        if(userRepository.exists(user.getLogin())){
            return false;
        }
        userRepository.save(user);
        return true;
    }
}