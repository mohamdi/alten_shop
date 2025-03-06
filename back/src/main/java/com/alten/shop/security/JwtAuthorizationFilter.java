package com.alten.shop.security;


import com.alten.shop.model.entity.User;
import com.alten.shop.repository.UserRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;
import java.util.Optional;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
    private final UserRepository repository;

    JwtAuthorizationFilter(
            AuthenticationManager authenticationManager, UserRepository repository) {
        super(authenticationManager);
        this.repository = repository;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String header = request.getHeader(JwtProperties.HEADER_STRING);

        if (header == null || !header.startsWith(JwtProperties.TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }

        Authentication authentication = getUsernamePasswordAuthentication(request);

        if (authentication != null) {
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } else {
            response.setStatus(403);
        }
        chain.doFilter(request, response);
    }

    private Authentication getUsernamePasswordAuthentication(HttpServletRequest request) {
        String token =
                request.getHeader(JwtProperties.HEADER_STRING).replace(JwtProperties.TOKEN_PREFIX, "");

        DecodedJWT validToken = null;
        try {
            validToken = JWT.require(HMAC512(JwtProperties.SECRET.getBytes())).build().verify(token);
        } catch (JWTVerificationException e) {
            logger.error("Token verification failed {}", e);
        }

        if (validToken != null) {
            String email = validToken.getSubject();
            Optional<User> user = repository.findByEmail(email);
            if (!user.isPresent()) return null;

            UserPrincipal principal = new UserPrincipal(user.get());
            return new UsernamePasswordAuthenticationToken(email, principal.getPassword(), principal.getAuthorities());
        }
        return null;
    }
}
