/**
 * Copyright 2022 European Commission. All rights reserved.
 * European Commission PROPRIETARY/CONFIDENTIAL.
 *
 **/
package com.sportradar.sukenik.world.cup.score.board.mapper.impl;

import org.jetbrains.annotations.Nullable;

import com.sportradar.sukenik.world.cup.score.board.data.model.TeamEntity;
import com.sportradar.sukenik.world.cup.score.board.dto.TeamDto;
import com.sportradar.sukenik.world.cup.score.board.mapper.AbstractEntityDtoMapper;

public class TeamEntityDtoMapper extends AbstractEntityDtoMapper<TeamEntity, TeamDto> {

    @Override
    @Nullable
    public TeamDto mapEntityToDto(@Nullable TeamEntity entity) {

        if (null == entity) {
            return null;
        }

        TeamDto dto = new TeamDto();
        dto.setName(entity.getName());
        dto.setScore(entity.getScore());
        return dto;
    }

    @Override
    @Nullable
    public TeamEntity mapDtoToEntity(@Nullable TeamDto dto) {

        if (null == dto) {
            return null;
        }

        TeamEntity entity = new TeamEntity();
        entity.setName(dto.getName());
        entity.setScore(dto.getScore());
        return entity;
    }
}
