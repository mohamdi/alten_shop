package com.alten.shop.model.dto;

import com.alten.shop.config.BaseDTO;
import com.alten.shop.model.enumeration.InventoryStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductDTO extends BaseDTO {
    private String code;
    private String name;
    private String description;
    private String image;
    private String category;
    private BigDecimal price;
    private Integer quantity;
    private String internalReference;
    private Integer shellId;
    private InventoryStatus inventoryStatus;
    private BigDecimal rating;

}
