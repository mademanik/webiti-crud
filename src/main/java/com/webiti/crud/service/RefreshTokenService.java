package com.webiti.crud.service;

import com.webiti.crud.model.RefreshToken;
import com.webiti.crud.model.User;
import com.webiti.crud.repository.RefreshTokenRepository;
import com.webiti.crud.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenService {

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;
    @Autowired
    private UserRepository userInfoRepository;

    @Transactional
    public RefreshToken createRefreshToken(String email) {
        Optional<User> optOfuser = userInfoRepository.findByEmail(email);
        if (optOfuser.isPresent()){
            User user = optOfuser.get();
            refreshTokenRepository.deleteByUserId(user.getId());
        }

        RefreshToken refreshToken = RefreshToken.builder()
                .user(userInfoRepository.findByEmail(email).get())
                .token(UUID.randomUUID().toString())
                .expiryDate(Instant.now().plusMillis(604800000).toEpochMilli()) // Convert to long
                .build();
        return refreshTokenRepository.save(refreshToken);
    }

    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    public Optional<RefreshToken> findByUserId(Integer userId) {
        return refreshTokenRepository.findByUserId(userId);
    }

    public Boolean isRefreshTokenExpired(RefreshToken token) {
        if (token.getExpiryDate() < Instant.now().toEpochMilli()) {
            return false;
        }
        return true;
    }

    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpiryDate() < Instant.now().toEpochMilli()) {
            refreshTokenRepository.delete(token);
            throw new RuntimeException(token.getToken() + " Refresh token was expired. Please make a new signin request");
        }
        return token;
    }

}
