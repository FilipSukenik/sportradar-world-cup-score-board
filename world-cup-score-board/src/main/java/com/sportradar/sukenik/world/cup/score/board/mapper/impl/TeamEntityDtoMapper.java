/**
 * Copyright 2022 European Commission. All rights reserved.
 * European Commission PROPRIETARY/CONFIDENTIAL.
 *
 **/
package com.sportradar.sukenik.world.cup.score.board.mapper.impl;

import com.sportradar.sukenik.world.cup.score.board.db.model.TeamEntity;
import com.sportradar.sukenik.world.cup.score.board.dto.TeamDto;
import com.sportradar.sukenik.world.cup.score.board.mapper.AbstractEntityDtoMapper;

public class TeamEntityDtoMapper extends AbstractEntityDtoMapper<TeamEntity, TeamDto> {

    @Override
    public TeamDto mapEntityToDto(TeamEntity entity) {

        TeamDto dto = new TeamDto();
        dto.setName(entity.getName());
        dto.setScore(entity.getScore());
        return dto;
    }

    @Override
    public TeamEntity mapDtoToEntity(TeamDto dto) {

        TeamEntity entity = new TeamEntity();
        entity.setName(dto.getName());
        entity.setScore(dto.getScore());
        return entity;
    }
}
