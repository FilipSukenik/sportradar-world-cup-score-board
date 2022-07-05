package com.sportradar.sukenik.world.cup.score.board.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import com.sportradar.sukenik.world.cup.score.board.data.ScoreBoardDaoImpl;
import com.sportradar.sukenik.world.cup.score.board.data.model.GameEntity;
import com.sportradar.sukenik.world.cup.score.board.dto.GameDto;
import com.sportradar.sukenik.world.cup.score.board.mapper.impl.GameEntityDtoMapper;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class ScoreBoardServiceImplTest {

    @Mock
    private ScoreBoardDaoImpl scoreBoardDao;

    @Mock
    private GameEntityDtoMapper mapper;

    @Spy
    @InjectMocks
    private ScoreBoardServiceImpl service;

    @Test
    void startGameIncorrectNamesTest() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> service.startGame(null, null));
        Assertions.assertThrows(IllegalArgumentException.class, () -> service.startGame("test-home-name", null));
        Assertions.assertThrows(IllegalArgumentException.class, () -> service.startGame("", "test-away-name"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> service.startGame("test-home-name", ""));
    }

    @Test
    void startGameCorrectTest() {

        GameDto response = new GameDto();

        doReturn(response).when(mapper).mapEntityToDto(any());

        Assertions.assertNotNull(service.startGame("test-home-team", "test-away-team"));
    }

    @Test
    void finishGameByIdNotFoundTest() {

        doReturn(Optional.empty()).when(scoreBoardDao).findGameById(any());

        Assertions.assertFalse(service.finishGameById(0));
    }

    @Test
    void finishGameByIdSuccessfulTest() {

        doReturn(Optional.of(new GameEntity())).when(scoreBoardDao).findGameById(any());
        doReturn(true).when(scoreBoardDao).removeGame(any());

        Assertions.assertTrue(service.finishGameById(0));
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void finishGameNullTest() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> service.finishGame(null));
    }

    @Test
    void finishGameSuccessfulTest() {

        doReturn(true).when(service).finishGameById(any());

        Assertions.assertTrue(service.finishGame(new GameDto()));
    }

    @Test
    void updateGameScoreNotFoundTest() {

        doReturn(Optional.empty()).when(scoreBoardDao).findGameById(0);

        Assertions.assertFalse(service.updateGameScore(0, 10, 10));
    }

    @Test
    void updateGameScoreSuccessfulTest() {

        doReturn(Optional.of(new GameEntity())).when(scoreBoardDao).findGameById(any());

        Assertions.assertTrue(service.updateGameScore(0, 10, 10));
    }

    @Test
    void getSummarySameScoreTest() {

        GameEntity game0 = new GameEntity(0, "test-home-team", "test-away-team");
        GameEntity game1 = new GameEntity(1, "test-home-team1", "test-away-team1");

        List<GameEntity> dbResult = new ArrayList<>(List.of(game0, game1));

        doReturn(dbResult).when(scoreBoardDao).getGameDbList();
        doCallRealMethod().when(mapper).mapAllEntitiesToDtos(any());
        doReturn(new GameDto(0)).when(mapper).mapEntityToDto(game0);
        doReturn(new GameDto(1)).when(mapper).mapEntityToDto(game1);

        List<GameDto> sortedResult = service.getSummary();

        Assertions.assertEquals(2, sortedResult.size());
        Assertions.assertEquals(game1.getGameId(), sortedResult.get(0).getGameId());
        Assertions.assertEquals(game0.getGameId(), sortedResult.get(1).getGameId());
    }
}