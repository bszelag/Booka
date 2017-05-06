package com.boot.security;

import com.boot.security.model.Credentials;
import com.boot.security.model.Session;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.UUID;

@Service
public interface AuthorizationService {

    public Optional<Session> getSession(UUID token);

    public boolean invalidateSession(UUID token);

    public Optional<Session> authenticate(Credentials credentials);
}
