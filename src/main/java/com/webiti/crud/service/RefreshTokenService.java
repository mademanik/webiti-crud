package com.webiti.crud.service;

import com.webiti.crud.model.RefreshToken;

import java.util.Optional;

public interface RefreshTokenService {
    RefreshToken createRefreshToken(String email);
    Optional<RefreshToken> findByToken(String token);
    Optional<RefreshToken> findByUserId(Integer userId);
    Boolean isRefreshTokenExpired(RefreshToken token);
    RefreshToken verifyExpiration(RefreshToken token);
}
