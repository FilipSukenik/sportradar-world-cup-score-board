package com.sportradar.sukenik.world.cup.score.board.data.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TeamEntityTest {

    @SuppressWarnings("ConstantConditions")
    @Test
    void nameConstructorIncorrectNameTest() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> new TeamEntity((String) null));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new TeamEntity(""));
    }

    @Test
    void nameConstructorCorrectNameTest() {

        TeamEntity team = new TeamEntity("test-name");

        Assertions.assertEquals("test-name", team.getName());
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void nameAndScoreConstructorIncorrectNameTest() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> new TeamEntity(null, 0));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new TeamEntity("", 0));
    }

    @Test
    void nameAndScoreConstructorIncorrectScoreTest() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> new TeamEntity("test-name", -10));
    }

    @Test
    void nameAndScoreCorrectTest() {

        TeamEntity team = new TeamEntity("test-name", 2);

        Assertions.assertEquals("test-name", team.getName());
        Assertions.assertEquals(2, team.getScore());
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void copyConstructorIncorrectValuesTest() {

        TeamEntity team = new TeamEntity();

        Assertions.assertThrows(IllegalArgumentException.class, () -> new TeamEntity((TeamEntity) null));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new TeamEntity(team));
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void setNameIncorrectValuesTest() {

        TeamEntity team = new TeamEntity();

        Assertions.assertThrows(IllegalArgumentException.class, () -> team.setName(null));
        Assertions.assertThrows(IllegalArgumentException.class, () -> team.setName(""));
    }

    @Test
    void setValueNegativeValuesTest() {

        TeamEntity team = new TeamEntity();

        Assertions.assertThrows(IllegalArgumentException.class, () -> team.setScore(-5));
    }

    @Test
    void setValueCorrectValuesTest() {

        TeamEntity team = new TeamEntity();

        Assertions.assertDoesNotThrow(() -> team.setScore(0));
        Assertions.assertDoesNotThrow(() -> team.setScore(3));
        Assertions.assertDoesNotThrow(() -> team.setScore(55));
        Assertions.assertDoesNotThrow(() -> team.setScore(180_400));
    }

    @Test
    void copyConstructorCorrectTest() {

        TeamEntity team = new TeamEntity("test-name", 10);

        TeamEntity copy = new TeamEntity(team);

        Assertions.assertEquals(team.getName(), copy.getName());
        Assertions.assertEquals(team.getScore(), copy.getScore());
    }
}