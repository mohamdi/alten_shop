package com.alten.shop.controller;

import com.alten.shop.model.dto.UserWishListDTO;
import com.alten.shop.model.mapper.UserWishListMapper;
import com.alten.shop.service.UserWishListService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/wishlist")
public class UserWishListController {

    private final UserWishListService service;
    private final UserWishListMapper mapper;

    public UserWishListController(UserWishListService service, UserWishListMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public void addProduct(@RequestParam Long productId) {
        service.addProduct(productId);
    }

    @DeleteMapping("/{id}")
    public void removeProduct(@PathVariable Long id) {
        service.removeProduct(id);
    }

    @GetMapping
    public ResponseEntity<List<UserWishListDTO>> findAll() {
        return ResponseEntity.ok(mapper.toDtos(service.findAll()));
    }


}
