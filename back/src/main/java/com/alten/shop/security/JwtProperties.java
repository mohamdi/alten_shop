package com.alten.shop.security;

import com.auth0.jwt.JWT;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Date;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;

public class JwtProperties {
    //TODO: These should be in application.properties
    public static final String SECRET = "aM>NK<NMS>AM<N<MSNKJDSAL<N<MLANKIHQ#*IUEBD*(#@(";
    public static final int EXPIRATION_TIME = 3600000;
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";

    public static String generateTokenFromPrincipal(UserPrincipal userPrincipal,
                                                    boolean isRefresh,
                                                    boolean isRememberMe) throws JsonProcessingException {
        TokenUser tokenUser = userPrincipal.toTokenUser();
        return JwtProperties.generateToken(tokenUser, isRefresh, isRememberMe);
    }

    public static String generateToken(TokenUser tokenUser, boolean isRefresh, boolean isRememberMe)
            throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        String user = objectMapper.writeValueAsString(tokenUser);

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        Date exp = new Date(nowMillis + JwtProperties.EXPIRATION_TIME);
        return JWT.create()
                .withIssuer("ALTEN")
                .withSubject(tokenUser.getEmail())
                .withClaim("user", user)
                .withNotBefore(now)
                .withIssuedAt(now)
                .withExpiresAt(exp)
                .sign(HMAC512(JwtProperties.SECRET.getBytes()));
    }
}
