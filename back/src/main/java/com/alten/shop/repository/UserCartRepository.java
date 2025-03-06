package com.alten.shop.repository;

import com.alten.shop.model.entity.UserCart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserCartRepository extends JpaRepository<UserCart, Long> {
    Optional<UserCart> findByUserIdAndProductId(Long userId, Long productId);
    List<UserCart> findAllByUserId(Long userId);
}
