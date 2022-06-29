/**
 * Copyright 2022 European Commission. All rights reserved.
 * European Commission PROPRIETARY/CONFIDENTIAL.
 *
 **/
package com.sportradar.sukenik.world.cup.score.board.mapper.impl;

import com.sportradar.sukenik.world.cup.score.board.db.model.GameEntity;
import com.sportradar.sukenik.world.cup.score.board.dto.GameDto;
import com.sportradar.sukenik.world.cup.score.board.mapper.AbstractEntityDtoMapper;

public class GameEntityDtoMapper extends AbstractEntityDtoMapper<GameEntity, GameDto> {

    public final TeamEntityDtoMapper teamMapper;

    public GameEntityDtoMapper(TeamEntityDtoMapper teamMapper) {

        this.teamMapper = teamMapper;
    }

    @Override
    public GameDto mapEntityToDto(GameEntity entity) {

        GameDto dto = new GameDto();

        dto.setGameId(entity.getGameId());
        dto.setHomeTeam(teamMapper.mapEntityToDto(entity.getHomeTeam()));
        dto.setAwayTeam(teamMapper.mapEntityToDto(entity.getAwayTeam()));
        dto.setGameId(entity.getGameId());

        return dto;
    }

    @Override
    public GameEntity mapDtoToEntity(GameDto dto) {

        GameEntity entity = new GameEntity();

        entity.setGameId(entity.getGameId());
        entity.setHomeTeam(teamMapper.mapDtoToEntity(dto.getHomeTeam()));
        entity.setAwayTeam(teamMapper.mapDtoToEntity(dto.getAwayTeam()));
        entity.setGameId(entity.getGameId());

        return entity;
    }
}
