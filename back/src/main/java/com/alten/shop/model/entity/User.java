package com.alten.shop.model.entity;

import com.alten.shop.config.BaseEntity;
import com.alten.shop.security.TokenUser;
import jakarta.persistence.Entity;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "app_user")
public class User extends BaseEntity {
    private String username;
    private String firstname;
    private String email;
    private String password;
    private Boolean admin;

    @PreUpdate
    @PrePersist
    private void preSave() {
        this.admin = false;
    }

    public TokenUser toTokenUser() {
        TokenUser tokenUser = new TokenUser();
        tokenUser.setId(getId());
        tokenUser.setEmail(getEmail());
        tokenUser.setAdmin(getAdmin());
        tokenUser.setPassword(null);
        return tokenUser;
    }
}
