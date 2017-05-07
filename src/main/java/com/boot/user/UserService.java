package com.boot.user;


import com.boot.user.model.User;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public interface UserService {

    public Collection<User> getAll();

    public Optional<User> getById(String id);

    public Optional<User> getByLogin(String login);

    public User modify(User user);

    public boolean delete(String id);

    public boolean add(User user);
}
