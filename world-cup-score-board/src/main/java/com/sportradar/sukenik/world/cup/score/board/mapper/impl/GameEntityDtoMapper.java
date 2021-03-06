package com.sportradar.sukenik.world.cup.score.board.mapper.impl;

import org.jetbrains.annotations.Nullable;

import com.sportradar.sukenik.world.cup.score.board.data.model.GameEntity;
import com.sportradar.sukenik.world.cup.score.board.dto.GameDto;
import com.sportradar.sukenik.world.cup.score.board.mapper.AbstractEntityDtoMapper;

/**
 * Specifies mapping between {@link GameEntity} and {@link GameDto}.
 */
public class GameEntityDtoMapper extends AbstractEntityDtoMapper<GameEntity, GameDto> {

    public final TeamEntityDtoMapper teamMapper;

    public GameEntityDtoMapper(TeamEntityDtoMapper teamMapper) {

        this.teamMapper = teamMapper;
    }

    @Override
    @Nullable
    public GameDto mapEntityToDto(@Nullable GameEntity entity) {

        if (null == entity) {
            return null;
        }

        GameDto dto = new GameDto();

        dto.setGameId(entity.getGameId());
        dto.setHomeTeam(teamMapper.mapEntityToDto(entity.getHomeTeam()));
        dto.setAwayTeam(teamMapper.mapEntityToDto(entity.getAwayTeam()));

        return dto;
    }

    @Override
    @Nullable
    public GameEntity mapDtoToEntity(@Nullable GameDto dto) {

        if (null == dto) {
            return null;
        }

        GameEntity entity = new GameEntity();

        entity.setGameId(dto.getGameId());
        entity.setHomeTeam(teamMapper.mapDtoToEntity(dto.getHomeTeam()));
        entity.setAwayTeam(teamMapper.mapDtoToEntity(dto.getAwayTeam()));

        return entity;
    }
}
