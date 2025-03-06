package com.alten.shop.security;

import com.alten.shop.model.entity.User;
import com.alten.shop.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("security")
public class AdminSecurityExpression {

    private final UserRepository userRepository;

    public AdminSecurityExpression(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean isAdmin(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return false;
        }

        Optional<User> user = userRepository.findByEmail(authentication.getName());
        return user.isPresent() && user.get().getAdmin();
    }
}
