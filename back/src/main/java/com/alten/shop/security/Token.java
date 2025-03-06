package com.alten.shop.security;


public class Token {
    public boolean authenticated = false;
    public String authToken;
    public TokenUser user;
    public String refreshToken;
    public Boolean isActive;
}
