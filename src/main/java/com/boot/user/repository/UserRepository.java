package com.boot.user.repository;

import com.boot.user.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface UserRepository extends CrudRepository<User, Integer> {

    User findByLogin(String login);

    User findByEmail(String email);

    @Query("SELECT u" +
            " FROM User u" +
            " WHERE UPPER(u.name) like UPPER(CONCAT('%',:any,'%')) " +
            " OR UPPER(u.surname) like UPPER(CONCAT('%',:any,'%')) " +
            " OR UPPER(u.login) like UPPER(CONCAT('%',:any,'%'))" +
            " OR UPPER(u.email) like UPPER(CONCAT('%',:any,'%'))")
    Collection<User> findByAny(@Param("any") String any);
}
