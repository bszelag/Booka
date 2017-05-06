package com.boot.security.impl;

import com.boot.security.AuthorizationService;
import com.boot.security.HashingService;
import com.boot.security.model.Credentials;
import com.boot.security.model.Session;
import com.boot.user.UserService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Component
public class AuthorizationServiceImpl implements AuthorizationService {

    private final Map<UUID, Session> sessions = new HashMap<>();

    @Autowired
    public HashingService hashingService;

    @Autowired
    private UserService userService;

    @Override
    public Optional<Session> getSession(UUID token) {
        return Optional.of(
                sessions.compute(token, (t, s) ->
                        Optional.ofNullable(s).
                                filter(es -> es.getExpiration().isAfter(Instant.now())).
                                orElse(null)));
    }

    @Override
    public boolean invalidateSession(UUID token) {
        return sessions.remove(token) != null;
    }

    @Override
    public Optional<Session> authenticate(Credentials credentials) {
        val session = userService.getByLogin(credentials.getLogin()).
                filter(u -> u.getPassword().equals(Base64.getEncoder().
                        encodeToString(hashingService.hash(credentials.getPassword().
                        toCharArray(), Base64.getDecoder().decode(u.getSalt()))))).
                map(u -> new Session(u));

        session.ifPresent(s -> sessions.put(s.getToken(), s));

        return session;
    }
}
