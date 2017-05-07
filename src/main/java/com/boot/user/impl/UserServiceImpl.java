package com.boot.user.impl;


import com.boot.security.HashingService;
import com.boot.user.UserService;
import com.boot.user.model.User;
import com.boot.user.repository.UserRepository;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public HashingService hashingService;

    @Override
    public Collection<User> getAll() {
        return StreamSupport.stream(userRepository.findAll().spliterator(), false).
                collect(Collectors.toList());
    }

    @Override
    public Optional<User> getById(String id) {
        return Optional.ofNullable(userRepository.findOne(id));
    }

    @Override
    public Optional<User> getByLogin(String login) {
        return Optional.ofNullable(userRepository.findByLogin(login));
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
    public User add(User user) {
        if(userRepository.exists(user.getLogin())){
            return null;
        }
        val salt = hashingService.generateSalt();
        user.setSalt(Base64.getEncoder().encodeToString(salt));
        user.setPassword(Base64.getEncoder().encodeToString(hashingService.hash(user.getPassword().toCharArray(),salt)));
        return userRepository.save(user);
    }
}
