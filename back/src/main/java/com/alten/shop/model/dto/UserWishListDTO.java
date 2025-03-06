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
public class UserWishListDTO extends BaseDTO {
    private UserDTO user;
    private ProductDTO product;
}
