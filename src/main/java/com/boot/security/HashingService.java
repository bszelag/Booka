package com.boot.security;

import org.springframework.stereotype.Service;

@Service
public interface HashingService {

    public byte[] generateSalt(int length);
    public byte[] generateSalt();
    public byte[] hash(char[] password, byte[] salt, int iterations, int keyLength);
    public byte[] hash(char[] password, byte[] salt);

}
