package com.sportradar.sukenik.world.cup.score.board.dependency.injection;

import java.util.HashMap;
import java.util.Map;

import org.jetbrains.annotations.NotNull;
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

    @SuppressWarnings("ConstantConditions")
    @Test
    void constructorHashMapNullTest() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> new NullObjectDependencyFactory(null));
    }

    @Test
    void constructorHashMapCorrectTest() {

        Map<Class<?>, Object> dependenciesMap = new HashMap<>();
        dependenciesMap.put(ScoreBoard.class, new ScoreBoardImpl());

        DependencyFactory dependencyFactory = new NullObjectDependencyFactory(dependenciesMap);

        Assertions.assertNotNull(dependencyFactory.getDependency(ScoreBoard.class));
    }

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

    @SuppressWarnings("ConstantConditions")
    @Test
    void addDependencyNullTest() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> dependencyFactory.addDependency(null, null));
        Assertions.assertThrows(IllegalArgumentException.class, () -> dependencyFactory.addDependency(Object.class, null));
    }

    @Test
    void getDependencyCorrectTest() {

        dependencyFactory.init();

        ScoreBoard scoreBoard = new ScoreBoardImpl();

        dependencyFactory.addDependency(ScoreBoard.class, scoreBoard);

        Assertions.assertEquals(scoreBoard, dependencyFactory.getDependency(ScoreBoard.class));
    }

    private static class NullObjectDependencyFactory extends DependencyFactory {

        public NullObjectDependencyFactory() {

        }

        public NullObjectDependencyFactory(
                @NotNull Map<Class<?>, Object> dependenciesMap) {

            super(dependenciesMap);
        }

        @Override
        protected void initDependencies() {

        }
    }
}