package com.alten.shop.controller;

import com.alten.shop.model.dto.UserCartDTO;
import com.alten.shop.model.mapper.UserCartMapper;
import com.alten.shop.service.UserCartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cart")
public class UserCartController {

    private final UserCartService service;
    private final UserCartMapper mapper;

    public UserCartController(UserCartService service, UserCartMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public void addProduct(@RequestParam Long productId) {
        service.addProduct(productId);
    }

    @PatchMapping
    public void updateQuantity(@RequestParam Long productId, @RequestParam Integer quantity) {
        service.updateQuantity(productId, quantity);
    }

    @DeleteMapping("/{id}")
    public void removeProduct(@PathVariable Long id) {
        service.removeProduct(id);
    }

    @GetMapping
    public ResponseEntity<List<UserCartDTO>> findAll() {
        return ResponseEntity.ok(mapper.toDtos(service.findAll()));
    }

}
