package com.sportradar.sukenik.world.cup.score.board.mapper.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import com.sportradar.sukenik.world.cup.score.board.data.model.TeamEntity;
import com.sportradar.sukenik.world.cup.score.board.dto.TeamDto;

@ExtendWith(MockitoExtension.class)
class TeamEntityDtoMapperTest {

    @Spy
    private TeamEntityDtoMapper mapper;

    @Test
    void mapEntityToDtoNullTest() {

        Assertions.assertNull(mapper.mapEntityToDto(null));
    }

    @Test
    void mapEntityToDtoCorrectTest() {

        TeamEntity entity = new TeamEntity("test-name", 10);

        TeamDto dto = mapper.mapEntityToDto(entity);

        Assertions.assertNotNull(dto);
        Assertions.assertEquals(entity.getName(), dto.getName());
        Assertions.assertEquals(entity.getScore(), dto.getScore());
    }

    @Test
    void mapDtoToEntityNullTest() {

        Assertions.assertNull(mapper.mapDtoToEntity(null));
    }

    @Test
    void mapDtoToEntityCorrectTest() {

        TeamDto dto = new TeamDto();
        dto.setName("test-name");
        dto.setScore(10);

        TeamEntity entity = mapper.mapDtoToEntity(dto);

        Assertions.assertNotNull(entity);
        Assertions.assertEquals(dto.getName(), entity.getName());
        Assertions.assertEquals(dto.getScore(), entity.getScore());
    }
}