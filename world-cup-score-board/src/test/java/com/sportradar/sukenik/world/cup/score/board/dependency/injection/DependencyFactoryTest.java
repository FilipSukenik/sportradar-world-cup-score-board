package com.sportradar.sukenik.world.cup.score.board.dependency.injection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import com.sportradar.sukenik.world.cup.score.board.ScoreBoard;
import com.sportradar.sukenik.world.cup.score.board.ScoreBoardImpl;
import com.sportradar.sukenik.world.cup.score.board.exception.TechnicalException;

@ExtendWith(MockitoExtension.class)
class DependencyFactoryTest {

    @Spy
    private NullObjectDependencyFactory dependencyFactory;

    @Test
    void getDependencyNotInitializedTest() {

        Assertions.assertThrows(IllegalStateException.class, () -> dependencyFactory.getDependency(Object.class));
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void getDependencyNullClassTest() {

        dependencyFactory.init();

        Assertions.assertThrows(IllegalArgumentException.class, () -> dependencyFactory.getDependency(null));
    }

    @Test
    void getDependencyNotFoundTest() {

        dependencyFactory.init();

        Assertions.assertThrows(TechnicalException.class, () -> dependencyFactory.getDependency(Object.class));
    }

    @Test
    void getDependencyCorrectTest() {

        dependencyFactory.init();

        ScoreBoard scoreBoard = new ScoreBoardImpl();

        dependencyFactory.addDependency(ScoreBoard.class, scoreBoard);

        Assertions.assertEquals(scoreBoard, dependencyFactory.getDependency(ScoreBoard.class));
    }

    private static class NullObjectDependencyFactory extends DependencyFactory {

        @Override
        protected void initDependencies() {

        }
    }
}