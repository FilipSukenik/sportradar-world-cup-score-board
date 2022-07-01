package com.sportradar.sukenik.world.cup.score.board.comaparator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import com.sportradar.sukenik.world.cup.score.board.data.model.GameEntity;
import com.sportradar.sukenik.world.cup.score.board.data.model.TeamEntity;

@ExtendWith(MockitoExtension.class)
class TotalScoreDescendingTimestampAscendingGameComparatorTest {

    @Spy
    private TotalScoreDescendingTimestampAscendingGameComparator comparator;

    @Test
    @SuppressWarnings("EqualsWithItself")
    void compareBothNullTest() {

        Assertions.assertEquals(0, comparator.compare(null, null));
    }

    @Test
    void compareFirstNullTest() {

        GameEntity game = new GameEntity(1, "test-home-team", "test-away-team");

        Assertions.assertEquals(1, comparator.compare(null, game));
    }

    @Test
    void compareSecondNullTest() {

        GameEntity game = new GameEntity(1, "test-home-team", "test-away-team");

        Assertions.assertEquals(-1, comparator.compare(game, null));
    }

    @Test
    @SuppressWarnings("EqualsWithItself")
    void compareSameEntityComparedTest() {

        GameEntity game = new GameEntity(1, "test-home-team", "test-away-team");

        Assertions.assertEquals(0, comparator.compare(game, game));
    }

    @Test
    void compareFirstTotalScoreMoreThanSecondTest() {

        GameEntity game1 = new GameEntity(1, "test-home-team", "test-away-team");
        game1.setHomeTeam(new TeamEntity("test-home-team", 4));
        game1.setAwayTeam(new TeamEntity("test-away-team", 3));

        GameEntity game2 = new GameEntity(1, "test-home-team", "test-away-team");
        game2.setHomeTeam(new TeamEntity("test-home-team", 5));
        game2.setAwayTeam(new TeamEntity("test-away-team", 1));

        Assertions.assertTrue(comparator.compare(game1, game2) < 0);
    }

    @Test
    void compareSecondTotalScoreMoreThanFirstTest() {

        GameEntity game1 = new GameEntity(1, "test-home-team", "test-away-team");
        game1.setHomeTeam(new TeamEntity("test-home-team", 2));
        game1.setAwayTeam(new TeamEntity("test-away-team", 3));

        GameEntity game2 = new GameEntity(2, "test-home-team", "test-away-team");
        game2.setHomeTeam(new TeamEntity("test-home-team", 5));
        game2.setAwayTeam(new TeamEntity("test-away-team", 1));

        Assertions.assertTrue(comparator.compare(game1, game2) > 0);
    }
}