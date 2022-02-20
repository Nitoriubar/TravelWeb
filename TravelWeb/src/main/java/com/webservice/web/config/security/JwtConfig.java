package com.webservice.web.config.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.webservice.web.oauth.token.AuthTokenProvider;

//Jwt를 사용하기 위한 설정
@Configuration
public class JwtConfig {
    @Value("${jwt.secret}")
    private String secret;

    @Bean
    public AuthTokenProvider jwtProvider() {
        return new AuthTokenProvider(secret);
    }
}
