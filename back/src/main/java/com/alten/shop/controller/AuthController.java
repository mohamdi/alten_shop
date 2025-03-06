package com.alten.shop.controller;

import com.alten.shop.exception.NotFoundException;
import com.alten.shop.model.dto.UserDTO;
import com.alten.shop.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService service;

    public AuthController(UserService service) {
        this.service = service;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserDTO dto) {
        try {
            return ResponseEntity.ok(service.login(dto.getEmail(), dto.getPassword()));
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

}
