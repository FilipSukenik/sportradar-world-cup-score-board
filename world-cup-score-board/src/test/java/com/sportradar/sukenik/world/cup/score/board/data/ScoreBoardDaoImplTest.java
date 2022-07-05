package com.sportradar.sukenik.world.cup.score.board.data;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import com.sportradar.sukenik.world.cup.score.board.data.model.GameEntity;

@ExtendWith(MockitoExtension.class)
class ScoreBoardDaoImplTest {

    @Mock
    private AtomicInteger idGenerator;

    @Spy
    @InjectMocks
    private ScoreBoardDaoImpl scoreBoardDao;

    @Test
    void getGameDbListEmptyDbTest() {

        Assertions.assertTrue(scoreBoardDao.getGameDbList().isEmpty());
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void saveNullTest() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> scoreBoardDao.save(null));
    }

    @Test
    void saveNoIdsTest() {

        GameEntity game1 = new GameEntity(null, "test-home-team", "test-away-team");
        GameEntity game2 = new GameEntity(null, "test-home-team1", "test-away-team1");

        scoreBoardDao.save(game1);
        scoreBoardDao.save(game2);

        Assertions.assertEquals(2, scoreBoardDao.getGameDbList().size());

        List<GameEntity> inDatabase = scoreBoardDao.getGameDbList();
        Assertions.assertEquals(0, inDatabase.get(0).getGameId());
        Assertions.assertEquals("test-home-team", inDatabase.get(0).getHomeTeam().getName());
        Assertions.assertEquals("test-away-team", inDatabase.get(0).getAwayTeam().getName());
        Assertions.assertEquals(1, inDatabase.get(1).getGameId());
        Assertions.assertEquals("test-home-team1", inDatabase.get(1).getHomeTeam().getName());
        Assertions.assertEquals("test-away-team1", inDatabase.get(1).getAwayTeam().getName());
    }

    @Test
    void saveEntityWithGameIdTest() {

        GameEntity game = new GameEntity(100, "test-home-team", "test-away-team");

        GameEntity storedGame = scoreBoardDao.save(game);

        Assertions.assertNotEquals(game.getGameId(), storedGame.getGameId());
    }

    @Test
    void saveUpdateValueOfProvidedInstanceTest() {

        GameEntity game = new GameEntity(null, "test-home-team", "test-away-team");

        scoreBoardDao.save(game);

        game.setGameId(100);
        GameEntity storedEntity = scoreBoardDao.getGameDbList().get(0);

        Assertions.assertEquals(0, storedEntity.getGameId());
    }

    @Test
    void saveUpdateValueOfReturnedInstanceTest() {

        GameEntity game = new GameEntity(null, "test-home-team", "test-away-team");

        GameEntity returnedEntity = scoreBoardDao.save(game);
        returnedEntity.setGameId(100);
        GameEntity storedEntity = scoreBoardDao.getGameDbList().get(0);

        Assertions.assertEquals(0, storedEntity.getGameId());
    }

    @Test
    void saveUpdateValueTest() {

        GameEntity game = new GameEntity(null, "test-home-team", "test-away-team");

        GameEntity returnedEntity = scoreBoardDao.save(game);

        returnedEntity.getHomeTeam().setName("updated-home-team");
        returnedEntity.getHomeTeam().setScore(10);
        returnedEntity.getAwayTeam().setScore(5);

        scoreBoardDao.save(returnedEntity);

        Assertions.assertEquals(1, scoreBoardDao.getGameDbList().size());

        GameEntity storedEntity = scoreBoardDao.getGameDbList().get(0);
        Assertions.assertEquals("updated-home-team", storedEntity.getHomeTeam().getName());
        Assertions.assertEquals(10, storedEntity.getHomeTeam().getScore());
        Assertions.assertEquals(5, storedEntity.getAwayTeam().getScore());
    }

    @Test
    void findGameByIdEmptyDatabaseTest() {

        Optional<GameEntity> gameOptional = scoreBoardDao.findGameById(0);

        Assertions.assertTrue(gameOptional.isEmpty());
    }

    @Test
    void findNonExistingGameTest() {

        GameEntity game = new GameEntity(null, "test-home-team", "test-away-team");
        scoreBoardDao.save(game);

        Optional<GameEntity> gameOptional = scoreBoardDao.findGameById(10);

        Assertions.assertTrue(gameOptional.isEmpty());
    }

    @Test
    void findExistingGameTest() {

        GameEntity game = new GameEntity(null, "test-home-team0", "test-away-team0");
        scoreBoardDao.save(game);
        game = new GameEntity(null, "test-home-team1", "test-away-team1");
        scoreBoardDao.save(game);
        game = new GameEntity(null, "test-home-team2", "test-away-team2");
        scoreBoardDao.save(game);
        game = new GameEntity(null, "test-home-team3", "test-away-team3");
        scoreBoardDao.save(game);

        Optional<GameEntity> gameOptional = scoreBoardDao.findGameById(2);

        Assertions.assertFalse(gameOptional.isEmpty());
        GameEntity foundEntity = gameOptional.get();
        Assertions.assertEquals(2, foundEntity.getGameId());
        Assertions.assertEquals("test-home-team2", foundEntity.getHomeTeam().getName());
        Assertions.assertEquals("test-away-team2", foundEntity.getAwayTeam().getName());
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void removeGameNullEntityTest() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> scoreBoardDao.removeGame(null));
    }

    @Test
    void removeGameNotStoredEntityTest() {

        Assertions.assertFalse(scoreBoardDao.removeGame(new GameEntity()));
    }

    @Test
    void removeGameNotStoredEntityCorrectTest() {

        GameEntity game = new GameEntity(null, "test-home-team0", "test-away-team0");

        GameEntity returnedGame = scoreBoardDao.save(game);

        Assertions.assertEquals(1, scoreBoardDao.getGameDbList().size());

        Assertions.assertTrue(scoreBoardDao.removeGame(returnedGame));

        Assertions.assertTrue(scoreBoardDao.getGameDbList().isEmpty());
    }
}