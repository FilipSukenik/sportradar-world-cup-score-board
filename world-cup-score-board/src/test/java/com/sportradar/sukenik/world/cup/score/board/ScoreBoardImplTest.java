package com.sportradar.sukenik.world.cup.score.board;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.sportradar.sukenik.world.cup.score.board.dependency.injection.DependencyFactory;
import com.sportradar.sukenik.world.cup.score.board.exception.TechnicalException;
import com.sportradar.sukenik.world.cup.score.board.service.ScoreBoardServiceImpl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ScoreBoardImplTest {

    private ScoreBoardImpl scoreBoard;

    private MockedDependencyFactory dependencyFactory;

    private ScoreBoard mockedService;

    @BeforeEach
    void setUp() {

        dependencyFactory = new MockedDependencyFactory();

        scoreBoard = new ScoreBoardImpl(dependencyFactory);

        mockedService = dependencyFactory.getDependency(ScoreBoard.class);
    }

    @Test
    void startGameExecutedTest() {

        scoreBoard.startGame("test-home-name", "test-away-name");

        verify(mockedService, times(1)).startGame(any(), any());
    }

    @Test
    void finishGameByIdExecutedTest() {

        scoreBoard.finishGameById(1);

        verify(mockedService, times(1)).finishGameById(any());
    }

    @Test
    void finishGameExecutedTest() {

        scoreBoard.finishGame(null);

        verify(mockedService, times(1)).finishGame(any());
    }

    @Test
    void updateGameScoreExecutedTest() {

        scoreBoard.updateGameScore(1, 10, 10);

        verify(mockedService, times(1)).updateGameScore(any(), eq(10), eq(10));
    }

    @Test
    void getSummaryExecutedTest() {

        scoreBoard.getSummary();

        verify(mockedService, times(1)).getSummary();
    }

    @Test
    void getMethodNameCorrectTest() {

        String methodName = scoreBoard.callMethod(() -> scoreBoard.getMethodName());

        Assertions.assertEquals("getMethodNameCorrectTest", methodName);
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void callMethodNullTest() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> scoreBoard.callMethod(null));
    }

    @Test
    void callMethodIllegalArgumentExceptionThrownTest() {

        IllegalArgumentException mockedException = new IllegalArgumentException();
        doThrow(mockedException).when(mockedService).getSummary();

        IllegalArgumentException returnedException =
                Assertions.assertThrows(IllegalArgumentException.class, () -> scoreBoard.callMethod(() -> mockedService.getSummary()));
        Assertions.assertEquals(mockedException, returnedException);
    }

    @Test
    void callMethodTechnicalExceptionThrownTest() {

        TechnicalException mockedException = new TechnicalException();
        doThrow(mockedException).when(mockedService).getSummary();

        TechnicalException returnedException =
                Assertions.assertThrows(TechnicalException.class, () -> scoreBoard.callMethod(() -> mockedService.getSummary()));
        Assertions.assertEquals(mockedException, returnedException);
    }

    @Test
    void callMethodRuntimeExceptionThrownTest() {

        RuntimeException mockedException = new RuntimeException();
        doThrow(mockedException).when(mockedService).getSummary();

        TechnicalException returnedException =
                Assertions.assertThrows(TechnicalException.class, () -> scoreBoard.callMethod(() -> mockedService.getSummary()));

        Assertions.assertNotNull(returnedException);
        Assertions.assertEquals(mockedException, returnedException.getCause());
    }

    private static class MockedDependencyFactory extends DependencyFactory {

        @Override
        protected void initDependencies() {

            addDependency(ScoreBoard.class, Mockito.mock(ScoreBoardServiceImpl.class));
        }
    }
}