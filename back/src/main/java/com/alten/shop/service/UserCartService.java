package com.alten.shop.service;

import com.alten.shop.exception.NotFoundException;
import com.alten.shop.model.entity.User;
import com.alten.shop.model.entity.UserCart;
import com.alten.shop.repository.UserCartRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserCartService {

    private final UserCartRepository repository;
    private final UserService userService;
    private final ProductService productService;

    public UserCartService(UserCartRepository repository, UserService userService, ProductService productService) {
        this.repository = repository;
        this.userService = userService;
        this.productService = productService;
    }

    public void addProduct(Long productId) {
        User user = userService.getConnectedUser();
        UserCart userCart = new UserCart();
        userCart.setProduct(productService.findById(productId));
        userCart.setQuantity(1);
        userCart.setUser(user);
        repository.save(userCart);
    }

    public void updateQuantity(Long productId, Integer quantity) {
        User user = userService.getConnectedUser();
        UserCart userCart = findByUserAndProduct(user.getId(), productId);
        userCart.setQuantity(quantity);
        repository.save(userCart);
    }

    private UserCart findByUserAndProduct(Long userId, Long productId) {
        return repository.findByUserIdAndProductId(userId, productId)
                .orElseThrow(() -> new NotFoundException("Order not found"));
    }

    public void removeProduct(Long productId) {
        User user = userService.getConnectedUser();
        UserCart userCart = findByUserAndProduct(user.getId(), productId);
        repository.delete(userCart);
    }

    public List<UserCart> findAll() {
        User user = userService.getConnectedUser();
        return repository.findAllByUserId(user.getId());
    }
}
