package com.alten.shop.model.entity;

import com.alten.shop.config.BaseEntity;
import com.alten.shop.model.enumeration.InventoryStatus;
import com.alten.shop.util.Util;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "product")
public class Product extends BaseEntity {
    @Column(unique = true)
    private String code;
    private String name;
    private String description;
    private String image;
    private String category;
    private BigDecimal price;
    private Integer quantity;
    @Column(unique = true)
    private String internalReference;
    private Integer shellId;
    @Enumerated(EnumType.STRING)
    private InventoryStatus inventoryStatus;
    private BigDecimal rating;

    @PrePersist
    @PreUpdate
    private void preSave() {
        if (this.price != null) {
            this.price = Util.scale(this.price);
        }
        if (this.rating != null) {
            this.rating = Util.scale(this.rating, 1);
        }
    }

}
