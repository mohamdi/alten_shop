package com.alten.shop.service;

import com.alten.shop.exception.NotFoundException;
import com.alten.shop.model.entity.User;
import com.alten.shop.repository.UserRepository;
import com.alten.shop.security.JwtProperties;
import com.alten.shop.security.TokenUser;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

@Slf4j
@Service
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public String login(String email, String password) {
        Optional<User> userOptional = repository.findByEmail(email);
        if (userOptional.isPresent() && isValid(userOptional.get(), password)) {
            User user = userOptional.get();
            try {
                TokenUser tokenUser = user.toTokenUser();

                return JwtProperties.generateToken(tokenUser, false, true);
            } catch (JsonProcessingException e) {
                log.error("Unable to generate auth token {}", Arrays.toString(e.getStackTrace()));
            }
        }
        throw new NotFoundException("User not found");
    }

    private boolean isValid(User user, String password) {
        return passwordEncoder.matches(password, user.getPassword());
    }
}
