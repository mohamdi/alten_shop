package com.alten.shop.security;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenUser {
    private Long id;
    private boolean admin;
    private String email;
    private String password;
}
