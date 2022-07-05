package com.sportradar.sukenik.world.cup.score.board.mapper;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.util.Assert;

/**
 * Provides a way of mapping of specific entity and dto. Both abstract methods ({@link #mapDtoToEntity(Object)} and {@link #mapEntityToDto(Object)})
 * needs to be overridden by children and provide implementation of how to map entity to dto and dto to entity.
 * Class also provides implementation of mapping of lists.
 * @param <E> entity class.
 * @param <D> dto class.
 */
public abstract class AbstractEntityDtoMapper<E, D> {

    @Nullable
    public abstract D mapEntityToDto(@Nullable E entity);

    @Nullable
    public abstract E mapDtoToEntity(@Nullable D dto);

    @NotNull
    public List<E> mapAllDtosToEntities(@NotNull List<D> dtoList) {

        Assert.notNull(dtoList, "dtoList cannot be null");

        return dtoList.stream().filter(Objects::nonNull)
                .map(this::mapDtoToEntity).collect(Collectors.toList());
    }

    @NotNull
    public List<D> mapAllEntitiesToDtos(@NotNull List<E> entityList) {

        return entityList.stream().filter(Objects::nonNull)
                .map(this::mapEntityToDto).collect(Collectors.toList());
    }
}
