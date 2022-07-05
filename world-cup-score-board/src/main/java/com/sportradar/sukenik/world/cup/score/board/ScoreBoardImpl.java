package com.sportradar.sukenik.world.cup.score.board;

import java.util.List;
import java.util.function.Supplier;

import org.jetbrains.annotations.NotNull;
import org.springframework.util.Assert;

import com.sportradar.sukenik.world.cup.score.board.dependency.injection.DefaultDependencyFactory;
import com.sportradar.sukenik.world.cup.score.board.dependency.injection.DependencyFactory;
import com.sportradar.sukenik.world.cup.score.board.dto.GameDto;
import com.sportradar.sukenik.world.cup.score.board.exception.TechnicalException;

/**
 * Serves as proxy to all business logic implemented by {@link com.sportradar.sukenik.world.cup.score.board.service.ScoreBoardServiceImpl}.
 * The proxy's goal is exception handling so each method inherited from {@link ScoreBoard} calls
 * same method on {@link com.sportradar.sukenik.world.cup.score.board.service.ScoreBoardServiceImpl}.
 * All exception handling is handled by {@link #callMethod(Supplier)}.
 *
 * Next purpose of this class is to handle dependency management. Client can call default constructor and
 * use {@link DefaultDependencyFactory} or he can provide his own implementation of {@link DependencyFactory}.
 * In provided dependency management client has to specify class which inherits from {@link ScoreBoard}
 * and dependency factory has to have stored this implementation by key ScoreBoard.class.
 */
public class ScoreBoardImpl implements ScoreBoard {

    private final ScoreBoard scoreBoardService;

    public ScoreBoardImpl() {

        DependencyFactory dependencyFactory = new DefaultDependencyFactory();
        dependencyFactory.init();
        scoreBoardService = dependencyFactory.getDependency(ScoreBoard.class);
    }

    public ScoreBoardImpl(DependencyFactory dependencyFactory) {

        dependencyFactory.init();
        scoreBoardService = dependencyFactory.getDependency(ScoreBoard.class);
    }

    @Override
    public GameDto startGame(String homeTeamName, String awayTeamName) {

        return callMethod(() -> scoreBoardService.startGame(homeTeamName, awayTeamName));
    }

    @Override
    public boolean finishGameById(Integer gameId) {

        return callMethod(() -> scoreBoardService.finishGameById(gameId));
    }

    @Override
    public boolean finishGame(GameDto gameDto) {

        return callMethod(() -> scoreBoardService.finishGame(gameDto));
    }

    @Override
    public boolean updateGameScore(Integer gameId, int newHomeTeamScore, int newAwayTeamScore) {

        return callMethod(() -> scoreBoardService.updateGameScore(gameId, newHomeTeamScore, newAwayTeamScore));
    }

    @Override
    public List<GameDto> getSummary() {

        return callMethod(scoreBoardService::getSummary);
    }

    /**
     * Calls supplier's method and handles exception thrown by call. In case of {@link IllegalArgumentException} and
     * {@link TechnicalException}, the exception is just rethrown, but in case of any other {@link Exception}, the exception
     * is wrapped by {@link TechnicalException}.
     * @param methodSupplier containing method that should be executed.
     * @param <O> generic for return type of original method.
     * @return value returned by calling original method.
     */
    <O> O callMethod(@NotNull Supplier<O> methodSupplier) {

        Assert.notNull(methodSupplier, "methodSupplier cannot be null");

        try {
            return methodSupplier.get();
        } catch (IllegalArgumentException | TechnicalException e) {
            throw e;
        } catch (Exception e) {
            throw new TechnicalException(String.format("Exception was thrown during execution of %s method", getMethodName()), e);
        }
    }

    /**
     * Extracts name of method called by {@link #callMethod(Supplier)}.
     * @return name of originally called method.
     */
    String getMethodName() {

        return new Throwable()
                .getStackTrace()[3]
                .getMethodName();
    }
}
