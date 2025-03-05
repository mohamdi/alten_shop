package com.alten.shop.model.mapper;

import java.util.List;

public interface BasicMapper<M, D> {
    M toEntity(D dto);

    D toDto(M entity);

    List<M> toEntities(List<D> dtos);

    List<D> toDtos(List<M> entities);
}
