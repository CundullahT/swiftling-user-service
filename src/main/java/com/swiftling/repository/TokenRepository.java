package com.swiftling.repository;

import com.swiftling.entity.Token;
import com.swiftling.enums.TokenType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {

    Optional<Token> findByTokenAndTokenType(String token, TokenType tokenType);

    @Modifying
    @Query("DELETE FROM Token t WHERE t.token = :token")
    void deleteByToken(@Param("token") String token);

}
