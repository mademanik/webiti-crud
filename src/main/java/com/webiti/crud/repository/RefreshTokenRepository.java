package com.webiti.crud.repository;

import com.webiti.crud.model.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Integer> {
    Optional<RefreshToken> findByToken(String token);

    Optional<RefreshToken> findByUserId(Integer userId);

    @Modifying
    @Query("delete from #{#entityName} e where e.user.id = ?1")
    void deleteByUserId(Integer userId);
}
