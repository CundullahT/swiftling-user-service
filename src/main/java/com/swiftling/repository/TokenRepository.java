package com.swiftling.repository;

import com.swiftling.entity.Token;
import com.swiftling.enums.TokenType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {

    Optional<Token> findByTokenAndTokenType(String token, TokenType tokenType);

    void deleteByToken(String token);

}
