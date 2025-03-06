package com.alten.shop.service;

import com.alten.shop.exception.NotFoundException;
import com.alten.shop.model.entity.User;
import com.alten.shop.model.entity.UserWishList;
import com.alten.shop.repository.UserWishListRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserWishListService {

    private final UserWishListRepository repository;
    private final UserService userService;
    private final ProductService productService;

    public UserWishListService(UserWishListRepository repository, UserService userService, ProductService productService) {
        this.repository = repository;
        this.userService = userService;
        this.productService = productService;
    }

    public void addProduct(Long productId) {
        User user = userService.getConnectedUser();
        UserWishList wishList = new UserWishList();
        wishList.setProduct(productService.findById(productId));
        wishList.setUser(user);
        repository.save(wishList);
    }


    public void removeProduct(Long id) {
        User user = userService.getConnectedUser();
        UserWishList wishList = findByUserAndProduct(user.getId(), id);
        repository.delete(wishList);
    }

    private UserWishList findByUserAndProduct(Long userId, Long productId) {
        return repository.findByUserIdAndProductId(userId, productId)
                .orElseThrow(() -> new NotFoundException("WishList not found"));
    }

    public List<UserWishList> findAll() {
        return repository.findAll();
    }
}
