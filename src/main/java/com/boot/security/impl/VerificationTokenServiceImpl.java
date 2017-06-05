package com.boot.security.impl;

import com.boot.security.HashingService;
import com.boot.security.VerificationTokenService;
import com.boot.security.model.VerificationToken;
import com.boot.security.repository.VerificationTokenRepository;
import com.boot.user.model.User;
import javassist.bytecode.ByteArray;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Optional;

@Component
public class VerificationTokenServiceImpl implements VerificationTokenService {

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    @Autowired
    private HashingService hashingService;

    @Override
    public VerificationToken create(User user) throws IllegalArgumentException {

        VerificationToken verificationToken = new VerificationToken();

        val salt = hashingService.generateSalt();
        String token = Base64.getEncoder().encodeToString(hashingService.hash(user.getLogin().toCharArray(),salt));

        token = token.replace("/","");
        verificationToken.setUser(user);
        verificationToken.setToken(token);
        verificationToken.setExpiryDate(verificationToken.calculateExpiryDate(VerificationToken.EXPIRATION));

        try {
          return verificationTokenRepository.save(verificationToken);
        }
        catch (DataAccessException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void delete(Integer id) throws IllegalArgumentException {

        if (id == null)
            throw new IllegalArgumentException("Cannot delete verification token with unspecified id");

        if (!verificationTokenRepository.exists(id))
            throw new IllegalArgumentException("Verification token does not exist");

        try {
            verificationTokenRepository.delete(id);
        }
        catch (DataAccessException e){
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public Optional<VerificationToken> getByToken(String token) {
        return Optional.ofNullable(verificationTokenRepository.findByToken(token));
    }
}
