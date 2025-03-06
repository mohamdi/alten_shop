package com.alten.shop.controller;

import com.alten.shop.exception.NotFoundException;
import com.alten.shop.model.dto.UserDTO;
import com.alten.shop.model.mapper.UserMapper;
import com.alten.shop.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService service;
    private final UserMapper mapper;

    public AuthController(UserService service, UserMapper mapper) {
        this.service = service;
        this.mapper = mapper;
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

    @PostMapping("/signup")
    public ResponseEntity signup(@RequestBody UserDTO dto) {
        try {
            service.signup(mapper.toEntity(dto));
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

}
