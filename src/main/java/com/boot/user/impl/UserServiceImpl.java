package com.boot.user.impl;


import com.boot.book.repository.BookRepository;
import com.boot.book.repository.BorrowedRepository;
import com.boot.security.HashingService;
import com.boot.user.UserService;
import com.boot.user.model.User;
import com.boot.user.repository.UserRepository;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BorrowedRepository borrowedRepository;

    @Override
    public Collection<User> getAll() {
        return StreamSupport.stream(userRepository.findAll().spliterator(), false).
                collect(Collectors.toList());
    }

    @Override
    public Optional<User> getById(Integer id) {
        return Optional.ofNullable(userRepository.findOne(id));
    }

    @Override
    public Optional<User> getByLogin(String login) {
        return Optional.ofNullable(userRepository.findByLogin(login));
    }

    @Override
    public User modify(User user) throws IllegalArgumentException {
        if (!userRepository.exists(user.getId()))
            throw new IllegalArgumentException("User does not exist");

        User originalUser = userRepository.findOne(user.getId());
        if (!user.getPassword().equals(originalUser.getPassword())) {
            val salt = hashingService.generateSalt();
            user.setSalt(Base64.getEncoder().encodeToString(salt));
            user.setPassword(Base64.getEncoder().encodeToString(hashingService.hash(user.getPassword().toCharArray(),salt)));
        }
        try {
            return userRepository.save(user); }
        catch (DataAccessException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void delete(Integer id) throws IllegalArgumentException {

        if (id == null)
            throw new IllegalArgumentException("Cannot delete user with unspecified id");

        if(userRepository.exists(id)){
            User user = userRepository.findOne(id);
            bookRepository.removeBookByUserId(id);
            String name = user.getName() + " " + user.getSurname();
            borrowedRepository.updateBorrowerName(id,name);
            borrowedRepository.removeBorrowedsByUserId(id, user.getEmail(), user.getFacebook());
            try {
                userRepository.delete(id);
            }
            catch (DataAccessException e){
                throw new IllegalArgumentException(e);
            }
        }
        throw new IllegalArgumentException("Cannot delete not existing user");
    }

    @Override
    public void hashAll() {
        userRepository.save(StreamSupport.stream(userRepository.findAll().spliterator(), false).
            map(user -> {
                val salt = hashingService.generateSalt();

                user.setSalt(Base64.getEncoder().encodeToString(salt));
                user.setPassword(Base64.getEncoder().encodeToString(hashingService.hash(user.getPassword().toCharArray(), salt)));

                return user;
            }).collect(Collectors.toList()));
    }

    @Override
    public User add(User user) throws IllegalArgumentException {
        if(userRepository.findByLogin(user.getLogin())!=null){
            throw new IllegalArgumentException("User with that login already exists");
        }

        if(userRepository.findByEmail(user.getEmail())!=null){
            throw new IllegalArgumentException("User with that email already exists");
        }

        val salt = hashingService.generateSalt();
        user.setSalt(Base64.getEncoder().encodeToString(salt));
        user.setPassword(Base64.getEncoder().encodeToString(hashingService.hash(user.getPassword().toCharArray(),salt)));
        try {
            return userRepository.save(user);
        }
        catch (DataAccessException e){
            throw new IllegalArgumentException(e);
        }
    }
}
