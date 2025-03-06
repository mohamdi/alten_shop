package com.alten.shop.model.mapper;

import com.alten.shop.model.dto.UserWishListDTO;
import com.alten.shop.model.entity.UserWishList;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserWishListMapper extends BasicMapper<UserWishList, UserWishListDTO> {
}
