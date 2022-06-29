/**
 * Copyright 2022 European Commission. All rights reserved.
 * European Commission PROPRIETARY/CONFIDENTIAL.
 *
 **/
package com.sportradar.sukenik.world.cup.score.board.mapper;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public abstract class AbstractEntityDtoMapper<E, D> {

    public abstract D mapEntityToDto(E entity);

    public abstract E mapDtoToEntity(D dto);

    public List<E> mapAllDtoToEntity(List<D> dtoList) {

        return dtoList.stream().filter(Objects::nonNull)
                .map(this::mapDtoToEntity).collect(Collectors.toList());
    }

    public List<D> mapAllEntityToDto(List<E> entityList) {

        return entityList.stream().filter(Objects::nonNull)
                .map(this::mapEntityToDto).collect(Collectors.toList());
    }
}
