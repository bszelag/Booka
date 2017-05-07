package com.boot.security.utility;

import com.boot.user.model.User;
import lombok.Getter;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Session {

    public static final String COOKIE_NAME = "session";
    public static final Duration VALIDITY_DURATION = Duration.of(15, ChronoUnit.MINUTES);

    @Getter
    private UUID token;

    @Getter
    private Instant expiration;

    @Getter
    private User user;

    private Map<String, Object> data = new HashMap<>();

    public Session(User user) {
        token = UUID.randomUUID();
        expiration = Instant.now().plus(VALIDITY_DURATION);
        this.user = user;
    }
}