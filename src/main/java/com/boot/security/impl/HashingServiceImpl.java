package com.boot.security.impl;

import com.boot.security.HashingService;
import lombok.val;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Random;


@Component
public class HashingServiceImpl implements HashingService {

    private static final int DEFAULT_ITERATIONS = 10;
    private static final int DEFAULT_KEY_LENGTH = 128 * 8;
    private static final int DEFAULT_SALT_LENGTH = 64;
    private final Random rng = new SecureRandom();

    @Override
    public byte[] generateSalt(int length){
        val salt = new byte[length];
        rng.nextBytes(salt);
        return salt;
    }

    @Override
    public byte[] generateSalt() {
        return generateSalt(DEFAULT_SALT_LENGTH);
    }

    @Override
    public byte[] hash(char[] password, byte[] salt, int iterations, int keyLength) {
        val spec = new PBEKeySpec(password, salt, iterations, keyLength);
        Arrays.fill(password, ' ');
        try {
            val factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            return factory.generateSecret(spec).getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new Error("Invalid hashing parameters: " + e.getMessage(), e);
        } finally {
            spec.clearPassword();
        }
    }

    @Override
    public byte[] hash(char[] password, byte[] salt) {
        return hash(password, salt, DEFAULT_ITERATIONS, DEFAULT_KEY_LENGTH);
    }

}
