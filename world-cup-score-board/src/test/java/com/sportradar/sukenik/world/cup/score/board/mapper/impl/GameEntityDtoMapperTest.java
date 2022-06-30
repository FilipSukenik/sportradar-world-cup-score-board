package com.sportradar.sukenik.world.cup.score.board.mapper.impl;

import java.util.Date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import com.sportradar.sukenik.world.cup.score.board.data.model.GameEntity;
import com.sportradar.sukenik.world.cup.score.board.dto.GameDto;
import com.sportradar.sukenik.world.cup.score.board.dto.TeamDto;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doCallRealMethod;

@ExtendWith(MockitoExtension.class)
class GameEntityDtoMapperTest {

    @Mock
    private TeamEntityDtoMapper teamMapper;

    @Spy
    @InjectMocks
    private GameEntityDtoMapper mapper;

    @Test
    void mapEntityToDtoNullTest() {

        Assertions.assertNull(mapper.mapEntityToDto(null));
    }

    @Test
    void mapEntityToDtoEmptyEntityTest() {

        GameEntity entity = new GameEntity();

        GameDto dto = mapper.mapEntityToDto(entity);

        Assertions.assertNotNull(dto);
        Assertions.assertNull(dto.getGameId());
        Assertions.assertNull(dto.getAwayTeam());
        Assertions.assertNull(dto.getHomeTeam());
        Assertions.assertNull(dto.getCreationTimestamp());
    }

    @Test
    void mapEntityToDtoCorrectTest() {

        GameEntity entity = new GameEntity(10, "test-home-name", "test-away-name");

        doCallRealMethod().when(teamMapper).mapEntityToDto(any());

        GameDto dto = mapper.mapEntityToDto(entity);

        Assertions.assertNotNull(dto);
        Assertions.assertEquals(entity.getGameId(), dto.getGameId());
        Assertions.assertNotNull(dto.getHomeTeam());
        Assertions.assertNotNull(dto.getAwayTeam());
        Assertions.assertEquals(entity.getCreationTimestamp().getTime(), dto.getCreationTimestamp().getTime());
    }

    @Test
    void mapDtoToEntityNullTest() {

        Assertions.assertNull(mapper.mapDtoToEntity(null));
    }

    @Test
    void mapDtoToEntityEmptyEntityTest() {

        GameDto dto = new GameDto();

        GameEntity entity = mapper.mapDtoToEntity(dto);

        Assertions.assertNotNull(entity);
        Assertions.assertNull(entity.getGameId());
        Assertions.assertNull(entity.getAwayTeam());
        Assertions.assertNull(entity.getHomeTeam());
        Assertions.assertNull(entity.getCreationTimestamp());
    }

    @Test
    void mapDtoToEntityCorrectTest() {

        GameDto dto = new GameDto();
        dto.setGameId(10);

        TeamDto team = new TeamDto();
        team.setName("test-home-name");
        dto.setHomeTeam(team);
        team = new TeamDto();
        team.setName("test-away-name");
        dto.setAwayTeam(team);
        dto.setCreationTimestamp(new Date());
        doCallRealMethod().when(teamMapper).mapDtoToEntity(any());

        GameEntity entity = mapper.mapDtoToEntity(dto);

        Assertions.assertNotNull(entity);
        Assertions.assertNotNull(entity.getHomeTeam());
        Assertions.assertNotNull(entity.getAwayTeam());
        Assertions.assertEquals(dto.getGameId(), entity.getGameId());
        Assertions.assertEquals(dto.getCreationTimestamp().getTime(), entity.getCreationTimestamp().getTime());
    }
}