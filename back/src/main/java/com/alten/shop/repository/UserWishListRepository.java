package com.alten.shop.repository;

import com.alten.shop.model.entity.UserWishList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserWishListRepository extends JpaRepository<UserWishList, Long> {
    Optional<UserWishList> findByUserIdAndProductId(Long userId, Long productId);
    List<UserWishList> findAllByUserId(Long userId);
}
