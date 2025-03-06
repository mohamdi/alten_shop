package com.alten.shop.model.dto;

import com.alten.shop.config.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDTO extends BaseDTO {
    private String username;
    private String firstname;
    private String email;
    private String password;
    private Boolean admin;
}
