package com.webiti.crud.repository;

import com.webiti.crud.model.RefreshToken;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, Integer> {
    Optional<RefreshToken> findByToken(String token);

    Optional<RefreshToken> findByUserId(Integer userId);

    @Modifying
    @Query("delete from #{#entityName} e where e.user.id = ?1")
    void deleteByUserId(Integer userId);
}
