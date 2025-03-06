package com.alten.shop.model.mapper;

import com.alten.shop.model.dto.UserDTO;
import com.alten.shop.model.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends BasicMapper<User, UserDTO> {
}
