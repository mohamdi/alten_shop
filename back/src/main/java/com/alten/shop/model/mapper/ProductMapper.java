package com.alten.shop.model.mapper;

import com.alten.shop.model.dto.ProductDTO;
import com.alten.shop.model.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper extends BasicMapper<Product, ProductDTO> {
}
