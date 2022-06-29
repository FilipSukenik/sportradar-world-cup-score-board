package com.sportradar.sukenik.world.cup.score.board.data.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class GameEntityTest {

    @SuppressWarnings("ConstantConditions")
    @Test
    void constructorIncorrectNamesTest() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> new GameEntity(0, null, null));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new GameEntity(0, "", null));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new GameEntity(0, null, ""));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new GameEntity(0, "", ""));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new GameEntity(0, "test-home-name", ""));
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void setHomeTeamNullTest() {

        GameEntity entity = new GameEntity();

        Assertions.assertThrows(IllegalArgumentException.class, () -> entity.setHomeTeam(null));
    }

    @Test
    void setHomeTeamCorrectTest() {

        GameEntity entity = new GameEntity();
        entity.setHomeTeam(new TeamEntity("test-name", 10));

        Assertions.assertNotNull(entity.getHomeTeam());
        Assertions.assertEquals("test-name", entity.getHomeTeam().getName());
        Assertions.assertEquals(10, entity.getHomeTeam().getScore());
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void setAwayTeamNullTest() {

        GameEntity entity = new GameEntity();

        Assertions.assertThrows(IllegalArgumentException.class, () -> entity.setAwayTeam(null));
    }

    @Test
    void setAwayTeamCorrectTest() {

        GameEntity entity = new GameEntity();
        entity.setAwayTeam(new TeamEntity("test-name", 10));

        Assertions.assertNotNull(entity.getAwayTeam());
        Assertions.assertEquals("test-name", entity.getAwayTeam().getName());
        Assertions.assertEquals(10, entity.getAwayTeam().getScore());
    }

    @Test
    void constructorCorrectTest() {

        GameEntity game = new GameEntity(1, "test-home-name", "test-away-name");

        Assertions.assertNotNull(game.getHomeTeam());
        Assertions.assertNotNull(game.getAwayTeam());
        Assertions.assertEquals(1, game.getGameId());
        Assertions.assertNotNull(game.getCreationTimestamp());
    }
}