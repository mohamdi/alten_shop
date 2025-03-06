package com.alten.shop.model.mapper;

import com.alten.shop.model.dto.UserCartDTO;
import com.alten.shop.model.entity.UserCart;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserCartMapper extends BasicMapper<UserCart, UserCartDTO> {
}
