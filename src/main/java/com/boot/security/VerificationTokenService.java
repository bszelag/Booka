package com.boot.security;

import com.boot.security.model.VerificationToken;
import com.boot.user.model.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface VerificationTokenService {

    VerificationToken create(User user) throws IllegalArgumentException;
    void delete(Integer id) throws IllegalArgumentException;
    Optional<VerificationToken> getByToken(String token);
}
