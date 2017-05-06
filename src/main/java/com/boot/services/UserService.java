package com.boot.services;


import com.boot.models.User;
import org.springframework.stereotype.Service;

import java.nio.file.OpenOption;
import java.util.Collection;

@Service
public interface UserService {

    public Collection<User> getAll();

    public User getById(String id);

    public User modify(User user);

    public boolean delete(String id);

    public boolean add(User user);
}
