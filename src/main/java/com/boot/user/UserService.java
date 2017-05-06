package com.boot.user;


import com.boot.user.model.User;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface UserService {

    public Collection<User> getAll();

    public User getById(String id);

    public User modify(User user);

    public boolean delete(String id);

    public boolean add(User user);
}
